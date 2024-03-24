package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Wall {
      private int x;
      private int y;
      private int width;
      private int length;
      private int rotate;

      public Wall (int x, int y, int width, int length) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.length = length;
      }
}
