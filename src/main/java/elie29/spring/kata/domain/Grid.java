package elie29.spring.kata.domain;

import elie29.spring.kata.domain.entity.Coordinate;
import elie29.spring.kata.domain.utils.Rules;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class Grid
{
   private List<Coordinate> obstacles = Collections.emptyList();

   @Getter()
   private boolean foundObstacle = false;

   public Grid(List<Coordinate> obstacles)
   {
      this.obstacles = obstacles;
   }

   public Coordinate move(Direction direction, Coordinate coordinate)
   {
      // In case obstacle is on the starting point
      foundObstacle = obstacles.contains(coordinate);

      if (foundObstacle) {
         return coordinate;
      }

      Coordinate newCoordinate = getCoordinate(direction, coordinate);

      foundObstacle = obstacles.contains(newCoordinate);

      return newCoordinate;
   }

   private Coordinate getCoordinate(Direction direction, Coordinate coordinate)
   {
      int x = coordinate.getX();
      int y = coordinate.getY();

      if (direction == Direction.N) {
         y = (y + 1) % Rules.MAX_HEIGHT;
         return new Coordinate(x, y);
      }

      if (direction == Direction.E) {
         x = (x + 1) % Rules.MAX_WIDTH;
         return new Coordinate(x, y);
      }

      if (direction == Direction.S) {
         y = y > 0 ? y - 1 : Rules.MAX_HEIGHT - 1;
         return new Coordinate(x, y);
      }

      // West
      x = x > 0 ? x - 1 : Rules.MAX_WIDTH - 1;
      return new Coordinate(x, y);
   }
}
