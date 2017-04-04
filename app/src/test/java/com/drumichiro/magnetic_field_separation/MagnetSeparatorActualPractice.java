package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.drumichiro.magnet_trajectory_separation.MagnetSeparatorTestHelper.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorActualPractice {

    private static final int FIELD_LINES = 3;
    private static final float[] neutralPosition = {
        9.625f, 214.375f, 174.3125f,
    };
    private static final float[][] rightHandLow = {
        {25.875f, 243.5f, 258.6875f},
        {22.25f, -40.8125f, 13.9375f},
        {-22.25f, 40.8125f, -13.9375f},
//        {0.0f, 0.0f, 0.0f},
//        {0.0f, 0.0f, 0.0f}
    };
    private static final float[][] rightHandMiddle = {
        {72.8125f, 141.9375f, 129.5625f},
        {-5.5625f, 3.3125f, 1.25f},
        {5.5625f, -3.3125f, -1.25f},
//        {0.0f, 0.0f, 0.0f},
//        {0.0f, 0.0f, 0.0f}
    };
    private static final float[][] rightHandHigh = {
        {87.3125f, 119.125f, 130.8125f},
        {-2.25f, 1.8125f, -1.625f},
        {2.25f, -1.8125f, 1.625f},
//        {0.0f, 0.0f, 0.0f},
//        {0.0f, 0.0f, 0.0f}
    };
    private static final float[][] leftHandLow = {
        {7.4375f, 230.125f, 203.875f},
        {7f, -2.9375f, -6.5625f},
        {-7f, 2.9375f, 6.5625f},
//        {0.0f, 0.0f, 0.0f},
//        {0.0f, 0.0f, 0.0f}
    };
    private static final float[][] leftHandMiddle = {
        {-46.125f, 194.375f, 138.375f},
        {5.9375f, 3.375f, 3.3125f},
        {-5.9375f, -3.375f, -3.3125f},
//        {0.0f, 0.0f, 0.0f},
//        {0.0f, 0.0f, 0.0f}
    };
    private static final float[][] leftHandHigh = {
        {-57.625f, 177.375f, 130.0f},
        {-1.875f, -3.3125f, -0.8125f},
        {1.875f, 3.3125f, 0.8125f},
//        {0.0f, 0.0f, 0.0f},
//        {0.0f, 0.0f, 0.0f}
    };

    private static final float[][] zeroPosition = {
        {0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f}
    };
    private static final float[][] relativeRightHandLow = new float[FIELD_LINES][MagnetSeparator.AXISES];
    private static final float[][] relativeRightHandMiddle = new float[FIELD_LINES][MagnetSeparator.AXISES];
    private static final float[][] relativeRightHandHigh = new float[FIELD_LINES][MagnetSeparator.AXISES];
    private static final float[][] relativeLeftHandLow = new float[FIELD_LINES][MagnetSeparator.AXISES];
    private static final float[][] relativeLeftHandMiddle = new float[FIELD_LINES][MagnetSeparator.AXISES];
    private static final float[][] relativeLeftHandHigh = new float[FIELD_LINES][MagnetSeparator.AXISES];

    private static final float[][][] rightHandTrain = {
        relativeRightHandHigh,
        relativeRightHandMiddle,
        relativeRightHandLow,
    };
    private static final float[][][] leftHandTrain = {
        relativeLeftHandHigh,
        relativeLeftHandMiddle,
        relativeLeftHandLow,
    };
    private static final int LARGE_TRAIN_NUM = 128;
    private static final float[][][] rightHandLargeTrain =
        new float[LARGE_TRAIN_NUM][FIELD_LINES][MagnetSeparator.AXISES];
    private static final float[][][] leftHandLargeTrain =
        new float[LARGE_TRAIN_NUM][FIELD_LINES][MagnetSeparator.AXISES];

    private float[][][][] prepareTestMagnet(float[][][][] range, int length) {
        float[][][][] magnet =
            new float[length][range.length][FIELD_LINES][MagnetSeparator.AXISES];
        for (int i1=0; i1<length; ++i1) {
            for (int i2=0; i2<range.length; ++i2) {
                for (int i3=0; i3<FIELD_LINES; ++i3) {
                    for (int i4=0; i4<MagnetSeparator.AXISES; ++i4) {
                        // x^2
                        float denom = (length - 1) * (length - 1);
                        float increased = (range[i2][1][i3][i4] - range[i2][0][i3][i4]) / denom;
                        magnet[i1][i2][i3][i4] = increased * i1 * i1 + range[i2][0][i3][i4];
                        // linear
//                        float denom = (length - 1);
//                        float increased = (range[i2][1][i3][i4] - range[i2][0][i3][i4]) / denom;
//                        magnet[i1][i2][i3][i4] = increased * i1 + range[i2][0][i3][i4];
                    }
                }
            }
        }
        // TODO: 差分を割り当てる
//        for (int i2=0; i2<range.length; ++i2) {
//            for (int i3 = 1; i3 < FIELD_LINES; ++i3) {
//                Arrays.fill(magnet[0][i2][i3], 0.0f);
//            }
//        }
//        for (int i1=1; i1<length; ++i1) {
//            for (int i2=0; i2<range.length; ++i2) {
//                for (int i3=1; i3<FIELD_LINES; ++i3) {
//                    for (int i4=0; i4<MagnetSeparator.AXISES; ++i4) {
//                        float difference = magnet[i1][i2][0][i4] - magnet[i1 - 1][i2][0][i4];
//                        magnet[i1][i2][i3][i4] = difference;
//                    }
//                }
//            }
//        }
        return magnet;
    }

    private float evaluateSeparation(float[][][][] range, int length) {
        return evaluateSeparation(range, length, leftHandTrain, rightHandTrain);
    }

    private float[][][] calculateObservation(float[][][][] magnet) {
        // magnet -> (length, num, line, axis)
        // observation -> (length, line, axis)
        float[][][] observation =
            new float[magnet.length][FIELD_LINES][MagnetSeparator.AXISES];
        for (int i1=0; i1<magnet.length; ++i1) {
            for (int i2=0; i2<magnet[i1].length; ++i2) {
                for (int i3=0; i3<FIELD_LINES; ++i3) {
                    for (int i4=0; i4<MagnetSeparator.AXISES; ++i4) {
                        observation[i1][i3][i4] += magnet[i1][i2][i3][i4];
                    }
                }
            }
        }
        return observation;
    }

    private float evaluateSeparation(float[][][][] range, int length,
                                     float[][][] leftTrain, float[][][] rightTrain) {
        // ----------------------------------------------------------
//        float[][][][] privMagnet = prepareTestMagnet(range, length);
//        length = 1;
//        float[][][][] magnet = new float[length][][][];
//        // length, label, line, axis
////        magnet[0] = privMagnet[94];
////        magnet[1] = privMagnet[95];
//        magnet[0] = privMagnet[94];
////        magnet[0][0][0] = privMagnet[94][0][0];
////        magnet[0][0][1] = privMagnet[94][0][1];
////        magnet[0][0][2] = privMagnet[94][0][2];
        // ----------------------------------------------------------
        float[][][][] magnet = prepareTestMagnet(range, length);
        float[][][] observation = calculateObservation(magnet);
        float[][][][] separation = allocateSameLength(magnet);
        MagnetSeparator separator = new MagnetSeparator();
        float[] constraint = new float[FIELD_LINES];
        constraint[0] = 100.0f;
        for (int i1=1; i1<constraint.length; ++i1) {
            constraint[i1] = 10.0f;
        }
        separator.setConstraint(constraint);
        float[] weight = new float[FIELD_LINES];
        Arrays.fill(weight, 1.0f);
        separator.setWeight(weight);
        //separator.skipIntensityInitialization(true);
        int[] trainsPerMagnet = new int[] {rightTrain.length, leftTrain.length};
        float[][] weightPerMagnet = new float[magnet.length][trainsPerMagnet.length];
        separator.allocateTrainBuffer(trainsPerMagnet);
        // relativeRightHandHigh,
        // relativeRightHandMiddle,
        // relativeRightHandLow,
        separator.setTrain(0, rightTrain);
        //separator.shrinkTrain(0);
        // relativeLeftHandHigh,
        // relativeLeftHandMiddle,
        // relativeLeftHandLow,
        separator.setTrain(1, leftTrain);
        //separator.shrinkTrain(1);

        separator.setExtendedTrainCombination(true);

        for (int i1=0; i1<magnet.length; ++i1) {
            separator.separateMagneticField(observation[i1], 300);
            separator.getSeparatedMagneticField(separation[i1]);
//            System.out.println(i1 + ":---------------------");
//            for (int i2=0; i2<trainsPerMagnet.length; ++i2) {
//                System.out.println("- " + i2 + ":--------");
//                for (int i3=0; i3<trainsPerMagnet[i2]; ++i3) {
////                    System.out.println(" - [" + i2 + "]: " + separator.getEstimatedIntegratedIntensity(i2, i3));
////                    float[] weight = separator.getEstimatedIntensity(i2, i3);
////                    for (float wt : weight) {
////                        System.out.println("    - : " + wt);
////                    }
//                    System.out.print(i3 + ":" + separator.getEstimatedIntegratedIntensity(i2, i3));
//                    float[] weight = separator.getEstimatedIntensity(i2, i3);
//                    for (float wt : weight) {
//                        System.out.print(", " + wt);
//                    }
//                    System.out.println("");
//                    weightPerMagnet[i1][i2] += separator.getEstimatedIntegratedIntensity(i2, i3);
//                }
//            }
        }
        // TODO: 評価軸をわけておく。distanceを複数返す？
        float[][][] tempMagnet = new float[magnet.length][magnet[0].length][MagnetSeparator.AXISES];
        float[][][] tempSep = new float[magnet.length][magnet[0].length][MagnetSeparator.AXISES];
//        for (int i3=1; i3<FIELD_LINES; ++i3) {
//            for (int i1=0; i1<magnet.length; ++i1) {
//                for (int i2=0; i2<magnet[i1].length; ++i2) {
//                    System.arraycopy(magnet[i1][i2][i3], 0, tempMagnet[i1][i2], 0, MagnetSeparator.AXISES);
//                    System.arraycopy(separation[i1][i2][i3], 0, tempSep[i1][i2], 0, MagnetSeparator.AXISES);
//                }
//            }
//            evaluateDistance(tempMagnet, tempSep, true);
//        }
        for (int i1=0; i1<magnet.length; ++i1) {
            for (int i2=0; i2<magnet[i1].length; ++i2) {
                System.arraycopy(magnet[i1][i2][0], 0, tempMagnet[i1][i2], 0, MagnetSeparator.AXISES);
                System.arraycopy(separation[i1][i2][0], 0, tempSep[i1][i2], 0, MagnetSeparator.AXISES);
            }
        }
        return evaluateDistance(tempMagnet, tempSep, true);
    }

    @Before
    public void setUp() {
        for (int i1=0; i1<MagnetSeparator.AXISES; ++i1) {
            relativeRightHandLow[0][i1] = rightHandLow[0][i1] - neutralPosition[i1];
            relativeRightHandMiddle[0][i1] = rightHandMiddle[0][i1] - neutralPosition[i1];
            relativeRightHandHigh[0][i1] = rightHandHigh[0][i1] - neutralPosition[i1];
            relativeLeftHandLow[0][i1] = leftHandLow[0][i1] - neutralPosition[i1];
            relativeLeftHandMiddle[0][i1] = leftHandMiddle[0][i1] - neutralPosition[i1];
            relativeLeftHandHigh[0][i1] = leftHandHigh[0][i1] - neutralPosition[i1];
        }
        for (int i1=1; i1<FIELD_LINES; ++i1) {
            for (int i2=0; i2<MagnetSeparator.AXISES; ++i2) {
                relativeRightHandLow[i1][i2] = rightHandLow[i1][i2];
                relativeRightHandMiddle[i1][i2] = rightHandMiddle[i1][i2];
                relativeRightHandHigh[i1][i2] = rightHandHigh[i1][i2];
                relativeLeftHandLow[i1][i2] = leftHandLow[i1][i2];
                relativeLeftHandMiddle[i1][i2] = leftHandMiddle[i1][i2];
                relativeLeftHandHigh[i1][i2] = leftHandHigh[i1][i2];
            }
        }

        float[][][][] range = new float[][][][] {
            {
                relativeRightHandHigh,
                relativeRightHandMiddle,
            },
            {
                relativeRightHandMiddle,
                relativeRightHandLow,
            },
            {
                relativeLeftHandHigh,
                relativeLeftHandMiddle,
            },
            {
                relativeLeftHandMiddle,
                relativeLeftHandLow,
            },
        };
        float[][][][] largeTrain = prepareTestMagnet(range, LARGE_TRAIN_NUM / 2);
        for (int i1=0; i1<largeTrain.length; ++i1) {
            for (int i2=0; i2<FIELD_LINES; ++i2) {
                for (int i3=0; i3<MagnetSeparator.AXISES; ++i3) {
                    int rs = i1 + largeTrain.length;
                    rightHandLargeTrain[i1][i2][i3] = largeTrain[i1][0][i2][i3];
                    rightHandLargeTrain[rs][i2][i3] = largeTrain[i1][1][i2][i3];
                    leftHandLargeTrain[i1][i2][i3] = largeTrain[i1][2][i2][i3];
                    leftHandLargeTrain[rs][i2][i3] = largeTrain[i1][3][i2][i3];
                }
            }
        }
    }

    @Test
    public void raisingRightHand() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                relativeRightHandLow,
                relativeRightHandHigh,
            },
            {
                zeroPosition,
                zeroPosition,
            },
        };
        int length = 100;
        float distance = evaluateSeparation(range, length);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }

    @Test
    public void raisingLeftHand() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                zeroPosition,
                zeroPosition,
            },
            {
                relativeLeftHandLow,
                relativeLeftHandHigh,
            },
        };
        int length = 100;
        float distance = evaluateSeparation(range, length);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }

    @Test
    public void raisingBothHands() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                relativeRightHandLow,
                relativeRightHandHigh,
            },
            {
                relativeLeftHandLow,
                relativeLeftHandHigh,
            },
        };
        // これ、なんで2500近くいくかチェックする
        int length = 100;
        float distance = evaluateSeparation(range, length);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }

    @Test
    public void droppingRightHand() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                relativeRightHandHigh,
                relativeRightHandLow,
            },
            {
                zeroPosition,
                zeroPosition,
            },
        };
        int length = 100;
        float distance = evaluateSeparation(range, length);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }

    @Test
    public void droppingLeftHand() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                zeroPosition,
                zeroPosition,
            },
            {
                relativeLeftHandHigh,
                relativeLeftHandLow,
            },
        };
        int length = 100;
        float distance = evaluateSeparation(range, length);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }

    @Test
    public void droppingBothHands() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                relativeRightHandHigh,
                relativeRightHandLow,
            },
            {
                relativeLeftHandHigh,
                relativeLeftHandLow,
            },
        };
        int length = 100;
        float distance = evaluateSeparation(range, length);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }

    @Test
    public void crossingBothHands() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                relativeRightHandLow,
                relativeRightHandHigh,
            },
            {
                relativeLeftHandHigh,
                relativeLeftHandLow,
            },
        };
        int length = 100;
        float distance = evaluateSeparation(range, length);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }

    @Test
    public void assignLargeTrain() throws Exception {
        float[][][][] range = new float[][][][] {
            {
                relativeRightHandLow,
                relativeRightHandHigh,
            },
            {
                zeroPosition,
                zeroPosition,
            },
        };
        // 片側のみの学習で収束するかやってみようかな… -> 意外といけるかも…
        // if (0.000001f > threshold) {
//        94 : index
//        expected[0] - (74.5846, -88.9684, -37.0417) [121.8619]
//        actual[0]   - (58.1581, -59.2673, -19.9224) [85.3924]
//        expected[1] - (0.0000, 0.0000, 0.0000) [0.0000]
//        actual[1]   - (-31.9321, -15.7121, -20.7383) [41.1899]
//        distance -> [3141.667969]
//
//        95 : index
//        expected[0] - (75.2052, -90.2247, -38.3333) [123.5547]
//        actual[0]   - (68.4764, -80.2838, -42.0138) [113.5767]
//        expected[1] - (0.0000, 0.0000, 0.0000) [0.0000]
//        actual[1]   - (-5.7805, -2.5083, -3.5740) [7.2443]
//        distance -> [210.123688]

        // if (0.0f > threshold) {
//        expected[0] - (74.5846, -88.9684, -37.0417) [121.8619]
//        actual[0]   - (58.0784, -59.1031, -19.7431) [85.1825]
//        expected[1] - (0.0000, 0.0000, 0.0000) [0.0000]
//        actual[1]   - (-33.3153, -15.8072, -21.4011) [42.6355]
//        distance -> [3281.418213]
//
//        expected[0] - (75.2052, -90.2247, -38.3333) [123.5547]
//        actual[0]   - (58.0175, -58.9769, -19.6028) [85.0210]
//        expected[1] - (0.0000, 0.0000, 0.0000) [0.0000]
//        actual[1]   - (-35.5418, -16.8690, -22.8391) [45.4907]
//        distance -> [3692.081299]

        //        int length = 2;
        int length = 100;
        float distance = evaluateSeparation(range, length, leftHandLargeTrain, rightHandLargeTrain);
        float borderline = 1000.0f * length;
        Assert.assertTrue(distance < borderline);
    }
}
