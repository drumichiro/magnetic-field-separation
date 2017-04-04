package com.drumichiro.magnet_trajectory_separation;

import java.lang.reflect.Field;
import java.util.Arrays;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorTestHelper {

    static public class TrainAssignment {
        public int[] index;
        public int[] lengthPerMagnet;
        public TrainAssignment(int magnets) {
            index = new int[magnets];
            lengthPerMagnet = new int[magnets];
            for (int i1=0; i1<magnets; ++i1) {
                index[i1] = i1;
                lengthPerMagnet[i1] = 1;
            }
        }
        public TrainAssignment(int magnets, int trains) {
            index = new int[magnets];
            lengthPerMagnet = new int[magnets];
            for (int i1=0; i1<magnets; ++i1) {
                index[i1] = i1;
                lengthPerMagnet[i1] = trains;
            }
        }
    }

    static private void printValue(float[] value, String caption) {
        String printFormat = " - (%3.4f, %3.4f, %3.4f) [%3.4f]";
        System.out.print(caption);
        float amp = value[0] * value[0] + value[1] * value[1] + value[2] * value[2];
        amp = (float)Math.sqrt(amp);
        System.out.println(String.format(printFormat, value[0], value[1], value[2], amp));
    }

    static public float evaluateDistance(float[][] expected, float[][] actual, boolean isPrinted) {
        float distance = 0.0f;
        for (int i1=0; i1<expected.length; ++i1) {
            if (isPrinted) {
                printValue(expected[i1], String.format("expected[%d]", i1));
                printValue(actual[i1],   String.format("actual[%d]  ", i1));
            }
            for (int i2 = 0; i2 < expected[i1].length; ++i2) {
                float diff = expected[i1][i2] - actual[i1][i2];
                distance += diff * diff;
            }
        }
        if (isPrinted) {
            System.out.println(String.format("distance -> [%3.6f]\n", distance));
        }
        return distance;
    }

    static public float evaluateDistance(float[][][] expected, float[][][] actual, boolean isPrinted) {
        float distance = 0.0f;
        for (int i1=0; i1<expected.length; ++i1) {
            distance += evaluateDistance(expected[i1], actual[i1], isPrinted);
        }
        if (isPrinted) {
            System.out.println(String.format("Total distance -> [%3.6f]\n", distance));
        }
        return distance;
    }

    static public float[] calculateObservation(float[][] magnet) {
        float[] weight = new float[magnet.length];
        Arrays.fill(weight, 1.0f);
        return calculateObservation(magnet, weight);
    }

    static public float[] calculateObservation(float[][] magnet, float[] weight) {
        int axises = magnet[0].length;
        float[] observation = new float[axises];
        for (int i1=0; i1<magnet.length; ++i1) {
            for (int i2=0; i2<axises; ++i2) {
                observation[i2] += magnet[i1][i2] * weight[i1];
            }
        }
        return observation;
    }

    static public float[][] calculateObservation(float[][][] magnet) {
        float[][] observation = new float[magnet.length][];
        for (int i1=0; i1<magnet.length; ++i1) {
            observation[i1] = calculateObservation(magnet[i1]);
        }
        return observation;
    }

    static public float[][] allocateSameLength(float[][] base) {
        return new float[base.length][base[0].length];
    }

    static public float[][][] allocateSameLength(float[][][] base) {
        return new float[base.length][base[0].length][base[0][0].length];
    }

    static public float[][][][] allocateSameLength(float[][][][] base) {
        return new float[base.length][base[0].length][base[0][0].length][base[0][0][0].length];
    }

    static public Object getPrivateMagnetFieldArrayValue(MagnetSeparator separator,
                                                         String fieldName, int label, int index) throws Exception {
        Field field = MagnetSeparator.class.getDeclaredField("trainBuffer");
        field.setAccessible(true);
        MagnetSeparator.MagnetTrainCluster[] magneticField = (MagnetSeparator.MagnetTrainCluster[])field.get(separator);
        Field subField = MagnetSeparator.MagneticField.class.getDeclaredField(fieldName);
        subField.setAccessible(true);
        return subField.get(magneticField[label].sample[index]);
    }
}
