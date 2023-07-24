package com.mas2022datascience.util;

public class Vector {
  /**
   * calculates the 3d vector of two points math:
   * vector = point2 - point1 = (x2-x1, y2-y1, z2-z1)
   * the actual point - the last point is used to calculate the vector
   * @param point1 of type double[] contains the x, y and z coordinates of the first point
   * @param point2 of type double[] contains the x, y and z coordinates of the second point
   * @return vector of the two points
   */
  public static double[] vector3dOf2Points(double[] point1, double[] point2) {

    if (point1 == null
        || point2 == null)
    {
      throw new NullPointerException();
    }
    int size = 3; // 3d vector
    if (point1.length != size || point2.length != size)
    {
      throw new IllegalArgumentException
          ("The size of the points must be " + size);
    }
    double[] vector = new double[size];
    for (int i = 0; i < size; i++)
    {
      vector[i] = point2[i] - point1[i];
    }
    return vector;
  }

  /**
   * calculates the magnitude of a 3d vector:
   * norm = sqrt(x^2+y^2+z^2)
   * @param vector of type double[] contains the x, y and z components of the vector
   * @return magnitude of the vector
   */
  public static double magnitudeOf3dVector(double[] vector) {
    double magnitude = 0;
    for (int i = 0; i < vector.length; i++) {
      magnitude += vector[i] * vector[i];
    }
    return Math.sqrt(magnitude);
  }

  /**
   * calculates the 3d vector of two points math:
   * vector = point2 - point1 = ((x2-x1)/magnitude, (y2-y1)/magnitude, (z2-z1)/magnitude)
   * the actual point - the last point is used to calculate the vector
   * @param point1 of type double[] contains the x, y and z coordinates of the first point
   * @param point2 of type double[] contains the x, y and z coordinates of the second point
   * @return normed vector of the two points
   */
  public static double[] normed3dVectorOf2Points(double[] point1, double[] point2) {

    double[] vector = vector3dOf2Points(point1, point2);
    double magnitude = magnitudeOf3dVector(vector);
    for (int i = 0; i < vector.length; i++) {
      vector[i] = vector[i] / magnitude;
    }
    return vector;
  }

  /**
   * calculates the dot product of two vectors
   * math: dot product = x1*x2 + y1*y2 + z1*z2
   * @param vector1 of type double[] contains the x, y and z components of the first vector
   * @param vector2 of type double[] contains the x, y and z components of the second vector
   * @return dot product of the two vectors
   */
  public static double dotProductOf2Vectors3d(double[] vector1, double[] vector2) {
    double dotProduct = 0;
    for (int i = 0; i < vector1.length; i++) {
      dotProduct += vector1[i] * vector2[i];
    }
    return dotProduct;
  }

  /**
   * calculates the projection of vector1 on vector2
   * math projection of vector 1 on vector 2 =
   * vector2 scalar product vector1 / (magnitude of vector2)^2 * vector2
   * @param vector1 of type double[] contains the x, y and z components of the first vector
   * @param vector2 of type double[] contains the x, y and z components of the second vector
   * @return dot product of the two vectors
   */
  public static double[] projectionOf3dVectorsOnAnother3dVector(double[] vector1, double[] vector2) {
    double[] vector = new double[3];
    double dotProduct = dotProductOf2Vectors3d(vector1, vector2);
    double magnitude = magnitudeOf3dVector(vector2);
    double multiplier = dotProduct / (magnitude * magnitude);
    vector[0] = vector2[0] * multiplier;
    vector[1] = vector2[1] * multiplier;
    vector[2] = vector2[2] * multiplier;
    return vector;
  }



}
