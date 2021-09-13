package com.co.poli.booking.models;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Showtime {
  private Long id;
  private Date date;
  private Long[] movies;
  private List<Movie> moviesObj;
}
