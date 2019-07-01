package elie29.spring.kata.domain;

import elie29.spring.kata.domain.entity.Coordinate;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class MarsRoverTest
{
   private MarsRover rover;

   @Before
   public void setUp()
   {
      rover = new MarsRover(new Grid());
   }

   @After
   public void tearDown()
   {
      rover = null;
   }

   @Test
   @Parameters({
      "R, 0:0:E",
      "RR, 0:0:S",
      "RRR, 0:0:W",
      "RRRR, 0:0:N",
   })
   public void shouldRotateRight(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "L, 0:0:W",
      "LL, 0:0:S",
      "LLL, 0:0:E",
      "LLLL, 0:0:N"
   })
   public void shouldRotateLeft(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "LR, 0:0:N",
      "LLRR, 0:0:N",
      "LLLRR, 0:0:W",
      "LLLLR, 0:0:E"
   })
   public void shouldRotateLeftAndRight(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "M, 0:1:N",
      "MM, 0:2:N",
      "MMMM, 0:4:N",
   })
   public void shouldMoveUp(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "MMMMMMMMMM, 0:0:N",
      "MMMMMMMMMMMM, 0:2:N",
   })
   public void shouldWrapUpWhenMovingNorth(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "RM, 1:0:E",
      "RMMM, 3:0:E",
   })
   public void shouldMoveRight(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "RMMMMMMMMMM, 0:0:E",
      "RMMMMMMMMMMMMM, 3:0:E",
   })
   public void shouldWrapUpWhenMovingEast(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "RRM, 0:9:S",
      "LLMMM, 0:7:S",
   })
   public void shouldMoveDown(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "RRMMMMMMMMMM, 0:0:S",
      "LLMMMMMMMMMMMMM, 0:7:S",
   })
   public void shouldWrapUpWhenMovingSouth(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "LM, 9:0:W",
      "LMMM, 7:0:W",
   })
   public void shouldMoveLeft(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "LMMMMMMMMMM, 0:0:W",
      "LMMMMMMMMMMMMM, 7:0:W",
   })
   public void shouldWrapUpWhenMovingWest(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "RMMLM, 2:1:N",
   })
   public void shouldMoveCorrectly(String command, String expected)
   {
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "M, O:0:0:N",
   })
   public void shouldDetectObstacleOnStartingPoint(String command, String expected)
   {
      rover = new MarsRover(new Grid(Arrays.asList(new Coordinate(0, 0))));
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "MM, O:0:2:N",
   })
   public void shouldDetectObstacleWhenHeadingNorth(String command, String expected)
   {
      rover = new MarsRover(new Grid(Arrays.asList(new Coordinate(0, 2))));
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "RMMRMM, O:2:0:E",
   })
   public void shouldStopOnFirstObstacleWhenHeadingEast(String command, String expected)
   {
      rover = new MarsRover(new Grid(Arrays.asList(new Coordinate(2, 0))));
      assertThat(rover.execute(command), is(expected));
   }

   @Test
   @Parameters({
      "RMMLMM, O:2:2:N",
      "MRMMLM, O:2:2:N",
   })
   public void shouldStopOnFirstObstacleWhenHeadingNorth(String command, String expected)
   {
      rover = new MarsRover(new Grid(Arrays.asList(new Coordinate(2, 2))));
      assertThat(rover.execute(command), is(expected));
   }
}
