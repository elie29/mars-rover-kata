package elie29.spring.kata.domain;

import elie29.spring.kata.domain.entity.Coordinate;
import elie29.spring.kata.domain.utils.Rules;

public class MarsRover
{
   private final Grid grid;
   private String obstacleIndication;

   public MarsRover(Grid grid)
   {
      this.grid = grid;
   }

   public String execute(String command)
   {
      // Default direction starting point
      Direction direction = Direction.N;
      Coordinate coordinate = new Coordinate(0, 0);
      obstacleIndication = Rules.EMPTY;

      for (char c : command.toCharArray()) {
         direction = rotate(c, direction);
         coordinate = move(direction, coordinate, c);
      }

      // 0:0:N or O:0:0:N
      return String.format("%s%s:%s:%s", obstacleIndication, coordinate.getX(), coordinate.getY(), direction.value());
   }

   private Direction rotate(char command, Direction direction)
   {
      // Stop rotation
      if (command == Rules.MOVE || foundObstacle()) {
         return direction;
      }

      if (command == Rules.ROTATE_RIGHT) {
         return direction.right();
      }

      // Rotate left
      return direction.left();
   }

   private Coordinate move(Direction direction, Coordinate coordinate, char c)
   {
      // stop on first obstacle
      if (c != Rules.MOVE || foundObstacle()) {
         return coordinate;
      }

      Coordinate newCoordinate = grid.move(direction, coordinate);

      if (grid.isFoundObstacle()) {
         obstacleIndication = Rules.OBSTACLE_INDICATION;
      }

      return newCoordinate;
   }

   private boolean foundObstacle()
   {
      return obstacleIndication.length() > 0;
   }
}
