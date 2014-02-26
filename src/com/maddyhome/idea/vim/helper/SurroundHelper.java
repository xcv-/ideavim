package com.maddyhome.idea.vim.helper;

public class SurroundHelper {
  private enum Surround {
    PARENS('(', ')'),
    CURLY_BRACES('{', '}'),
    BRACKETS('[', ']'),
    ANGLE_BRACKETS('<','>');

    private char lhs, rhs;

    Surround(char lhs, char rhs) {
      this.lhs = lhs;
      this.rhs = rhs;
    }
  }

  private static Surround findSurround(char toGuess) {
    for (Surround surround : Surround.values()) {
      if (surround.lhs == toGuess || surround.rhs == toGuess)
        return surround;
    }

    return null;
  }

  public static char lhs(char toGuess) {
    Surround surround = findSurround(toGuess);

    return surround == null? toGuess : surround.lhs;
  }

  public static char rhs(char toGuess) {
    Surround surround = findSurround(toGuess);

    return surround == null? toGuess : surround.rhs;
  }
}
