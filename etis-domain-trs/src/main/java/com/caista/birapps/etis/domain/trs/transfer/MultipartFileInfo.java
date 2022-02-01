package com.caista.birapps.etis.domain.trs.transfer;

public class MultipartFileInfo {
  private String filename;
  private String contentType;
  private String path;

  
  public MultipartFileInfo() {
    super();
  }

  public MultipartFileInfo(String filename, String contentType, String path) {
    super();
    this.filename = filename;
    this.contentType = contentType;
    this.path = path;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }



}
