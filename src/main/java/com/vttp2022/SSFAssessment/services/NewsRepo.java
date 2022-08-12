package com.vttp2022.SSFAssessment.services;

import com.vttp2022.SSFAssessment.models.Data;

public interface NewsRepo {
  public int save(final Data data);

  public Data findById(final String newsId);
  
}
