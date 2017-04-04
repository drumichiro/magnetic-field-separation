package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Test;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorMultiClass {

    @Test
    public void separatingThree() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1, 1});
        separator.setTrain(0, new float[][] { {-3.0f, -2.0f, 8.0f}, });
        separator.setTrain(1, new float[][] { {-2.0f, 1.0f, -2.0f}, });
        separator.setTrain(2, new float[][] { {-5.0f, 1.0f, 4.0f}, });
        separator.separateMagneticField(observation, 30);
        separator.getSeparatedMagneticField(separation);
        float margin = 0.1f;
        Assert.assertTrue(-3.0f - margin < separation[0][0]);
        Assert.assertTrue(-3.0f + margin > separation[0][0]);
        Assert.assertTrue(-2.0f - margin < separation[0][1]);
        Assert.assertTrue(-2.0f + margin > separation[0][1]);
        Assert.assertTrue(8.0f - margin < separation[0][2]);
        Assert.assertTrue(8.0f + margin > separation[0][2]);
        Assert.assertTrue(-2.0f - margin < separation[1][0]);
        Assert.assertTrue(-2.0f + margin > separation[1][0]);
        Assert.assertTrue(1.0f - margin < separation[1][1]);
        Assert.assertTrue(1.0f + margin > separation[1][1]);
        Assert.assertTrue(-2.0f - margin < separation[1][2]);
        Assert.assertTrue(-2.0f + margin > separation[1][2]);
        Assert.assertTrue(-5.0f - margin < separation[2][0]);
        Assert.assertTrue(-5.0f + margin > separation[2][0]);
        Assert.assertTrue(1.0f - margin < separation[2][1]);
        Assert.assertTrue(1.0f + margin > separation[2][1]);
        Assert.assertTrue(4.0f - margin < separation[2][2]);
        Assert.assertTrue(4.0f + margin > separation[2][2]);
    }

    @Test
    public void separatingFour() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1, 1, 1});
        separator.setTrain(0, new float[][] { {-3.0f, -2.0f, 8.0f}, });
        separator.setTrain(1, new float[][] { {-2.0f, 1.0f, -2.0f}, });
        separator.setTrain(2, new float[][] { {-1.0f, -2.0f, 3.0f}, });
        separator.setTrain(3, new float[][] { {-4.0f, 3.0f, 1.0f}, });
        separator.separateMagneticField(observation, 30);
        separator.getSeparatedMagneticField(separation);
        float margin = 0.1f;
        Assert.assertTrue(-3.0f - margin < separation[0][0]);
        Assert.assertTrue(-3.0f + margin > separation[0][0]);
        Assert.assertTrue(-2.0f - margin < separation[0][1]);
        Assert.assertTrue(-2.0f + margin > separation[0][1]);
        Assert.assertTrue(8.0f - margin < separation[0][2]);
        Assert.assertTrue(8.0f + margin > separation[0][2]);
        Assert.assertTrue(-2.0f - margin < separation[1][0]);
        Assert.assertTrue(-2.0f + margin > separation[1][0]);
        Assert.assertTrue(1.0f - margin < separation[1][1]);
        Assert.assertTrue(1.0f + margin > separation[1][1]);
        Assert.assertTrue(-2.0f - margin < separation[1][2]);
        Assert.assertTrue(-2.0f + margin > separation[1][2]);
        Assert.assertTrue(-1.0f - margin < separation[2][0]);
        Assert.assertTrue(-1.0f + margin > separation[2][0]);
        Assert.assertTrue(-2.0f - margin < separation[2][1]);
        Assert.assertTrue(-2.0f + margin > separation[2][1]);
        Assert.assertTrue(3.0f - margin < separation[2][2]);
        Assert.assertTrue(3.0f + margin > separation[2][2]);
        Assert.assertTrue(-4.0f - margin < separation[3][0]);
        Assert.assertTrue(-4.0f + margin > separation[3][0]);
        Assert.assertTrue(3.0f - margin < separation[3][1]);
        Assert.assertTrue(3.0f + margin > separation[3][1]);
        Assert.assertTrue(1.0f - margin < separation[3][2]);
        Assert.assertTrue(1.0f + margin > separation[3][2]);
    }

    @Test
    public void separatingFive() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1, 1, 1, 1});
        separator.setTrain(0, new float[][] { {-3.0f, -2.0f, 8.0f}, });
        separator.setTrain(1, new float[][] { {-2.0f, 1.0f, -2.0f}, });
        separator.setTrain(2, new float[][] { {-1.0f, -2.0f, 3.0f}, });
        separator.setTrain(3, new float[][] { {-2.0f, 4.0f, -2.0f}, });
        separator.setTrain(4, new float[][] { {-2.0f, -1.0f, 3.0f}, });
        separator.separateMagneticField(observation, 30);
        separator.getSeparatedMagneticField(separation);
        float margin = 0.1f;
        Assert.assertTrue(-3.0f - margin < separation[0][0]);
        Assert.assertTrue(-3.0f + margin > separation[0][0]);
        Assert.assertTrue(-2.0f - margin < separation[0][1]);
        Assert.assertTrue(-2.0f + margin > separation[0][1]);
        Assert.assertTrue(8.0f - margin < separation[0][2]);
        Assert.assertTrue(8.0f + margin > separation[0][2]);
        Assert.assertTrue(-2.0f - margin < separation[1][0]);
        Assert.assertTrue(-2.0f + margin > separation[1][0]);
        Assert.assertTrue(1.0f - margin < separation[1][1]);
        Assert.assertTrue(1.0f + margin > separation[1][1]);
        Assert.assertTrue(-2.0f - margin < separation[1][2]);
        Assert.assertTrue(-2.0f + margin > separation[1][2]);
        Assert.assertTrue(-1.0f - margin < separation[2][0]);
        Assert.assertTrue(-1.0f + margin > separation[2][0]);
        Assert.assertTrue(-2.0f - margin < separation[2][1]);
        Assert.assertTrue(-2.0f + margin > separation[2][1]);
        Assert.assertTrue(3.0f - margin < separation[2][2]);
        Assert.assertTrue(3.0f + margin > separation[2][2]);
        Assert.assertTrue(-2.0f - margin < separation[3][0]);
        Assert.assertTrue(-2.0f + margin > separation[3][0]);
        Assert.assertTrue(4.0f - margin < separation[3][1]);
        Assert.assertTrue(4.0f + margin > separation[3][1]);
        Assert.assertTrue(-2.0f - margin < separation[3][2]);
        Assert.assertTrue(-2.0f + margin > separation[3][2]);
        Assert.assertTrue(-2.0f - margin < separation[4][0]);
        Assert.assertTrue(-2.0f + margin > separation[4][0]);
        Assert.assertTrue(-1.0f - margin < separation[4][1]);
        Assert.assertTrue(-1.0f + margin > separation[4][1]);
        Assert.assertTrue(3.0f - margin < separation[4][2]);
        Assert.assertTrue(3.0f + margin > separation[4][2]);
    }
}
