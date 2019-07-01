package elie29.spring.kata.domain;

public enum Direction
{
   N {
      @Override
      public Direction right()
      {
         return E;
      }

      @Override
      public Direction left()
      {
         return W;
      }
   },
   E {
      @Override
      public Direction right()
      {
         return S;
      }

      @Override
      public Direction left()
      {
         return N;
      }
   },
   S {
      @Override
      public Direction right()
      {
         return W;
      }

      @Override
      public Direction left()
      {
         return E;
      }
   },
   W {
      @Override
      public Direction right()
      {
         return N;
      }

      @Override
      public Direction left()
      {
         return S;
      }
   };

   public String value()
   {
      return name();
   }

   public abstract Direction right();

   public abstract Direction left();
}
