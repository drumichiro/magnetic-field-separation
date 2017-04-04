package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Test;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorFundamental {

    @Test
    public void noTraining() throws Exception {
        float[] observation = {-1.0f, 0.0f, 1.0f};
        float[][] separation = { {0.0f, 0.0f, 0.0f}, };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1});
        separator.separateMagneticField(observation, 1);
        separator.getSeparatedMagneticField(separation);
    }

    @Test
    public void noIteration() throws Exception {
        float[] observation = {-1.0f, 0.0f, 1.0f};
        float[][] separation = { {0.0f, 0.0f, 0.0f}, };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1});
        separator.setTrain(0, new float[][] { observation });
        separator.separateMagneticField(observation, 0);
        separator.getSeparatedMagneticField(separation);
        Assert.assertEquals(-1.0f, separation[0][0]);
        Assert.assertEquals(0.0f, separation[0][1]);
        Assert.assertEquals(1.0f, separation[0][2]);
    }

    @Test
    public void noSeparation() throws Exception {
        float[] observation = {-1.0f, 0.0f, 1.0f};
        float[][] separation = { {0.0f, 0.0f, 0.0f}, };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1});
        separator.setTrain(0, new float[][] { {-0.8f, -0.2f, 1.3f}, });
        separator.separateMagneticField(observation, 1);
        separator.getSeparatedMagneticField(separation);
        float margin = 0.2f;
        Assert.assertTrue(-0.8f - margin < separation[0][0]);
        Assert.assertTrue(-0.8f + margin > separation[0][0]);
        Assert.assertTrue(-0.2f - margin < separation[0][1]);
        Assert.assertTrue(0.2f + margin > separation[0][1]);
        Assert.assertTrue(1.3f - margin < separation[0][2]);
        Assert.assertTrue(1.3f + margin > separation[0][2]);
    }

    @Test
    public void separatingTwo() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        separator.setTrain(0, new float[][] { {-3.0f, -2.0f, 8.0f}, });
        separator.setTrain(1, new float[][] { {-7.0f, 2.0f, 2.0f}, });
        separator.separateMagneticField(observation, 30);
        separator.getSeparatedMagneticField(separation);
        float margin = 0.1f;
        Assert.assertTrue(-3.0f - margin < separation[0][0]);
        Assert.assertTrue(-3.0f + margin > separation[0][0]);
        Assert.assertTrue(-2.0f - margin < separation[0][1]);
        Assert.assertTrue(-2.0f + margin > separation[0][1]);
        Assert.assertTrue(8.0f - margin < separation[0][2]);
        Assert.assertTrue(8.0f + margin > separation[0][2]);
        Assert.assertTrue(-7.0f - margin < separation[1][0]);
        Assert.assertTrue(-7.0f + margin > separation[1][0]);
        Assert.assertTrue(2.0f - margin < separation[1][1]);
        Assert.assertTrue(2.0f + margin > separation[1][1]);
        Assert.assertTrue(2.0f - margin < separation[1][2]);
        Assert.assertTrue(2.0f + margin > separation[1][2]);
    }
}
