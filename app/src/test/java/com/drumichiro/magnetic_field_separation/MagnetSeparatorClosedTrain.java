package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Test;

import static com.drumichiro.magnet_trajectory_separation.MagnetSeparatorTestHelper.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorClosedTrain {

    private float[][][] prepareTestMagnet(float[][][] range, int length) {
        int axises = range[0][0].length;
        float[][][] magnet = new float[length][range.length][axises];
        for (int i1=0; i1<length; ++i1) {
            for (int i2=0; i2<range.length; ++i2) {
                for (int i3=0; i3<axises; ++i3) {
                    float increased = (range[i2][1][i3] - range[i2][0][i3]) / (length - 1);
                    magnet[i1][i2][i3] = increased * i1 + range[i2][0][i3];
                }
            }
        }
        return magnet;
    }

    private float evaluateSeparation(float[][][] range, int length) {
        float[][][] magnet = prepareTestMagnet(range, length);
        float[][] observation = calculateObservation(magnet);
        float[][][] separation = allocateSameLength(magnet);
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        for (int i1=0; i1<magnet.length; ++i1) {
            for (int i2=0; i2<magnet[i1].length; ++i2) {
                separator.setTrain(i2, new float[][] {magnet[i1][i2]} );
            }
            separator.separateMagneticField(observation[i1], 10);
            separator.getSeparatedMagneticField(separation[i1]);
        }
        return evaluateDistance(magnet, separation, true);
    }

    @Test
    public void zeroValueCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
            },
            {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void positiveAscendingCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {100.0f, 100.0f, 100.0f}
            },
            {
                {0.0f, 0.0f, 0.0f},
                {100.0f, 200.0f, 300.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void oneSideAscendingCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
            },
            {
                {0.0f, 0.0f, 0.0f},
                {200.0f, 100.0f, 300.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void oneAxisAscendingCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
            },
            {
                {0.0f, 0.0f, -100.0f},
                {0.0f, 0.0f, 200.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void negativeDescendingCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {-100.0f, -100.0f, -100.0f}
            },
            {
                {0.0f, 0.0f, 0.0f},
                {-100.0f, -200.0f, -300.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void oneSideDescendingCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {-200.0f, -100.0f, -300.0f}
            },
            {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
            }
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void oneAxisDescendingCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {0.0f, 200.0f, 0.0f},
                {0.0f, -100.0f, 0.0f}
            },
            {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void parallelOrderCase() throws Exception {
        float[][][] range = new float[][][] {
            {
                {100.0f, 100.0f, 100.0f},
                {100.0f, 100.0f, 100.0f}
            },
            {
                {-100.0f, -100.0f, -100.0f},
                {-100.0f, -100.0f, -100.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void crossOrderCase() throws Exception {
        // Cannot separate exact cross case.
        float[][][] range = new float[][][] {
            {
                {-100.0f, -100.0f, -100.0f},
                {100.0f, 100.0f, 100.0f}
            },
            {
                {100.0f, 100.0f, 100.0f},
                {-100.0f, -100.0f, -100.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void positiveCrossOrderCase() throws Exception {
        // Cannot separate exact cross case.
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {100.0f, 100.0f, 100.0f}
            },
            {
                {200.0f, 200.0f, 200.0f},
                {100.0f, 100.0f, 100.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void negativeCrossOrderCase() throws Exception {
        // Cannot separate exact cross case.
        float[][][] range = new float[][][] {
            {
                {0.0f, 0.0f, 0.0f},
                {-100.0f, -100.0f, -100.0f}
            },
            {
                {-200.0f, -200.0f, -200.0f},
                {-100.0f, -100.0f, -100.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void simpleCase1() throws Exception {
        float[][][] range = new float[][][] {
            {
                {200.0f, 0.0f, 0.0f},
                {100.0f, 100.0f, 100.0f}
            },
            {
                {200.0f, 200.0f, 200.0f},
                {100.0f, -100.0f, 100.0f}
            }
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void simpleCase2() throws Exception {
        float[][][] range = new float[][][] {
            {
                {-100.0f, 0.0f, 0.0f},
                {200.0f, 100.0f, 100.0f}
            },
            {
                {200.0f, 200.0f, 200.0f},
                {100.0f, -100.0f, 100.0f}
            },
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }

    @Test
    public void simpleCase3() throws Exception {
        float[][][] range = new float[][][] {
            {
                {-100.0f, -200.0f, 0.0f},
                {200.0f, 100.0f, 100.0f}
            },
            {
                {200.0f, -200.0f, -200.0f},
                {100.0f, -100.0f, 0.0f}
            }
        };
        float distance = evaluateSeparation(range, 4);
        Assert.assertTrue(distance < 100.0f);
    }
}
