/*
 * Modified by: fuentem
 * Last updated: Jul 26, 2018 2:22:53 PM
 */
package com.caista.birapps.etis.domain.trs.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

/**
 * The Class PhoneticSearchUtil.
 */
public class PhoneticSearchUtil {

  /**
   * Checks if is match.
   *
   * @param srcString the src string
   * @param otherString the other string
   * @return true, if is match
   */
  public static boolean isMatch(String srcString, String otherString) {
    boolean flag = false;

    if (StringUtils.isNotBlank(otherString)) {
      Soundex soundex = new Soundex();
      flag = soundex.soundex(srcString).equals(soundex.soundex(otherString));
    }
    return flag;
  }

  /**
   * Search by single property.
   *
   * @param <T> the generic type
   * @param searchStr the search str
   * @param objList the obj list
   * @param objProperty the obj property
   * @return the list
   * @throws NoSuchFieldException the no such field exception
   * @throws SecurityException the security exception
   * @throws IllegalArgumentException the illegal argument exception
   * @throws IllegalAccessException the illegal access exception
   */
  public static <T> List<T> searchBySingleProperty(String searchStr, List<T> objList,
      String objProperty) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
      IllegalAccessException {
    List<T> matchedObj = new ArrayList<T>();

    for (T obj : objList) {
      Field findField = ReflectionUtils.findField(obj.getClass(), objProperty);
      findField.setAccessible(true);
      if (isMatch(searchStr, (String) findField.get(obj))) {
        matchedObj.add(obj);
      }
    }
    return matchedObj;
  }

  /**
   * Search by obj properties.
   *
   * @param <T> the generic type
   * @param searchParam the search param
   * @param searchParamProperties the search param properties
   * @param objList the obj list
   * @return the list
   * @throws IllegalArgumentException the illegal argument exception
   * @throws IllegalAccessException the illegal access exception
   * @throws NoSuchFieldException the no such field exception
   * @throws SecurityException the security exception
   */
  public static <T> List<T> searchByObjProperties(Object searchParam,
      String[] searchParamProperties, List<T> objList) throws IllegalArgumentException,
      IllegalAccessException, NoSuchFieldException, SecurityException {
    List<T> matchedObj = new ArrayList<T>();

    int counter = 0;
    for (Field field : searchParam.getClass().getDeclaredFields()) {

      field.setAccessible(true);
      String searchStr = (String) field.get(searchParam);

      if (Arrays.asList(searchParamProperties).contains(field.getName())
          && StringUtils.isNotBlank(searchStr)) {
        matchedObj = searchBySingleProperty(searchStr, (counter == 0 || matchedObj.size() == 0)
            ? objList
            : matchedObj, field.getName());
      }
      counter++;
    }
    return matchedObj;
  }


}
