package arcade.intro;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class EdgeOfTheOcean {


  private int adjacentElementsProduct(int[] inputArray) {
    int previus = 0;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < inputArray.length; i++) {
      if (i == 0) {
        previus = inputArray[i];
      } else if (previus * inputArray[i] > max) {
        max = previus * inputArray[i];
        previus = inputArray[i];
      } else {
        previus = inputArray[i];
      }
    }
    return max;
  }

  private int shapeArea(int n) {
    return (n * n) + ((n - 1) * (n - 1));
  }

  private int makeArrayConsecutive2(int[] statues) {
    Arrays.sort(statues);
    int quantidade = 0;
    for (int i = 0; i < statues.length - 1; i++) {
      int atual = statues[i];
      int proximo = statues[i + 1];

      if (atual + 1 != proximo) {
        while (atual + 1 != proximo) {
          quantidade++;
          atual++;
        }
      }
    }

    return quantidade;
  }

  @Test
  public void testAdjacentElementsProduct() {
    int[] array = {3, 6, -2, -5, 7, 3};

    System.out.println(adjacentElementsProduct(array));
    assertEquals(adjacentElementsProduct(array), 21);

    array = new int[]{3, 6, 5, 10, 7, 3};
    System.out.println(adjacentElementsProduct(array));
    assertEquals(adjacentElementsProduct(array), 70);

    array = new int[]{1, 2, 3, 0};
    System.out.println(adjacentElementsProduct(array));
    assertEquals(adjacentElementsProduct(array), 6);

    array = new int[]{9, 5, 10, 2, 24, -1, -48};
    System.out.println(adjacentElementsProduct(array));
    assertEquals(adjacentElementsProduct(array), 50);

    array = new int[]{-23, 4, -3, 8, -12};
    System.out.println(adjacentElementsProduct(array));
    assertEquals(adjacentElementsProduct(array), -12);

  }

  @Test
  public void testshapeArea() {
    System.out.println(shapeArea(2));
    assertEquals(shapeArea(2), 5);

    System.out.println(shapeArea(3));
    assertEquals(shapeArea(3), 13);

    System.out.println(shapeArea(4));
    assertEquals(shapeArea(4), 25);

  }

  @Test
  public void testMakeArrayConsecutive2() {
    int[] statues = new int[]{6, 2, 3, 8};
    int quantidade = makeArrayConsecutive2(statues);
    System.out.println(quantidade);
    assertEquals(3, quantidade);

    statues = new int[]{0, 3};
    quantidade = makeArrayConsecutive2(statues);
    System.out.println(quantidade);
    assertEquals(2, quantidade);

    statues = new int[]{5, 4, 6};
    quantidade = makeArrayConsecutive2(statues);
    System.out.println(quantidade);
    assertEquals(0, quantidade);

    statues = new int[]{6, 3};
    quantidade = makeArrayConsecutive2(statues);
    System.out.println(quantidade);
    assertEquals(2, quantidade);

    statues = new int[]{1};
    quantidade = makeArrayConsecutive2(statues);
    System.out.println(quantidade);
    assertEquals(0, quantidade);

  }
}
