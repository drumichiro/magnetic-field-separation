package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Test;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorAdaptability {

    private float[][] transformForm(float[] train, float ratio) {
        for (int i1=0; i1<train.length; ++i1) {
             train[i1] *= ratio;
        }
        return new float[][] { train };
    }

    @Test
    public void sameAngleSmallAmplitudeTrain() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        separator.setTrain(0, transformForm(new float[] {-3.0f, -2.0f, 8.0f}, 0.5f));
        separator.setTrain(1, transformForm(new float[] {-7.0f, 2.0f, 2.0f}, 0.5f));
        separator.separateMagneticField(observation, 10);
        separator.getSeparatedMagneticField(separation);
        Assert.assertTrue(-1.5f - 0.1f < separation[0][0]);
        Assert.assertTrue(-1.5f + 0.1f > separation[0][0]);
        Assert.assertTrue(-1.0f - 0.1f < separation[0][1]);
        Assert.assertTrue(-1.0f + 0.1f > separation[0][1]);
        Assert.assertTrue(4.0f - 0.1f < separation[0][2]);
        Assert.assertTrue(4.0f + 0.1f > separation[0][2]);
        Assert.assertTrue(-3.5f - 0.1f < separation[1][0]);
        Assert.assertTrue(-3.5f + 0.1f > separation[1][0]);
        Assert.assertTrue(1.0f  - 0.1f < separation[1][1]);
        Assert.assertTrue(1.0f  + 0.1f > separation[1][1]);
        Assert.assertTrue(1.0f - 0.1f < separation[1][2]);
        Assert.assertTrue(1.0f + 0.1f > separation[1][2]);

        separator.skipIntensityNormalization(true);
        separator.separateMagneticField(observation, 10);
        separator.getSeparatedMagneticField(separation);
        Assert.assertTrue(-3.0f - 0.1f < separation[0][0]);
        Assert.assertTrue(-3.0f + 0.1f > separation[0][0]);
        Assert.assertTrue(-2.0f - 0.1f < separation[0][1]);
        Assert.assertTrue(-2.0f + 0.1f > separation[0][1]);
        Assert.assertTrue(8.0f - 0.1f < separation[0][2]);
        Assert.assertTrue(8.0f + 0.1f > separation[0][2]);
        Assert.assertTrue(-7.0f - 0.1f < separation[1][0]);
        Assert.assertTrue(-7.0f + 0.1f > separation[1][0]);
        Assert.assertTrue(2.0f  - 0.1f < separation[1][1]);
        Assert.assertTrue(2.0f  + 0.1f > separation[1][1]);
        Assert.assertTrue(2.0f - 0.1f < separation[1][2]);
        Assert.assertTrue(2.0f + 0.1f > separation[1][2]);
    }

    @Test
    public void sameAngleLargeAmplitudeTrain() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        separator.setTrain(0, transformForm(new float[] {-3.0f, -2.0f, 8.0f}, 2.0f));
        separator.setTrain(1, transformForm(new float[] {-7.0f, 2.0f, 2.0f}, 2.0f));
        separator.separateMagneticField(observation, 10);
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

        separator.skipIntensityInitialization(true);
        separator.separateMagneticField(observation, 10);
        separator.getSeparatedMagneticField(separation);
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

    @Test
    public void sameAngleSmallAmplitudeTrainAndZeroValueTrain() throws Exception {
        float[] observation = {-3.0f, -2.0f, 8.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        separator.setTrain(0, transformForm(observation.clone(), 0.5f));
        separator.setTrain(1, transformForm(new float[] {0.0f, 0.0f, 0.0f}, 0.0f));
        separator.separateMagneticField(observation, 10);
        separator.getSeparatedMagneticField(separation);
        Assert.assertTrue(-1.5f - 0.1f < separation[0][0]);
        Assert.assertTrue(-1.5f + 0.1f > separation[0][0]);
        Assert.assertTrue(-1.0f - 0.1f < separation[0][1]);
        Assert.assertTrue(-1.0f + 0.1f > separation[0][1]);
        Assert.assertTrue(4.0f - 0.1f < separation[0][2]);
        Assert.assertTrue(4.0f + 0.1f > separation[0][2]);
        Assert.assertTrue(0.0f - 0.1f < separation[1][0]);
        Assert.assertTrue(0.0f + 0.1f > separation[1][0]);
        Assert.assertTrue(0.0f  - 0.1f < separation[1][1]);
        Assert.assertTrue(0.0f  + 0.1f > separation[1][1]);
        Assert.assertTrue(0.0f - 0.1f < separation[1][2]);
        Assert.assertTrue(0.0f + 0.1f > separation[1][2]);

        separator.skipIntensityNormalization(true);
        separator.separateMagneticField(observation, 10);
        separator.getSeparatedMagneticField(separation);
        Assert.assertTrue(-3.0f - 0.1f < separation[0][0]);
        Assert.assertTrue(-3.0f + 0.1f > separation[0][0]);
        Assert.assertTrue(-2.0f - 0.1f < separation[0][1]);
        Assert.assertTrue(-2.0f + 0.1f > separation[0][1]);
        Assert.assertTrue(8.0f - 0.1f < separation[0][2]);
        Assert.assertTrue(8.0f + 0.1f > separation[0][2]);
        Assert.assertTrue(0.0f - 0.1f < separation[1][0]);
        Assert.assertTrue(0.0f + 0.1f > separation[1][0]);
        Assert.assertTrue(0.0f  - 0.1f < separation[1][1]);
        Assert.assertTrue(0.0f  + 0.1f > separation[1][1]);
        Assert.assertTrue(0.0f - 0.1f < separation[1][2]);
        Assert.assertTrue(0.0f + 0.1f > separation[1][2]);
    }

    @Test
    public void sameAngleLargeAmplitudeTrainAndZeroValueTrain() throws Exception {
        float[] observation = {-7.0f, 2.0f, 2.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        separator.setTrain(0, transformForm(new float[] {0.0f, 0.0f, 0.0f}, 0.0f));
        separator.setTrain(1, transformForm(observation.clone(), 2.0f));
        separator.separateMagneticField(observation, 10);
        separator.getSeparatedMagneticField(separation);
        float margin = 0.1f;
        Assert.assertTrue(0.0f - margin < separation[0][0]);
        Assert.assertTrue(0.0f + margin > separation[0][0]);
        Assert.assertTrue(0.0f - margin < separation[0][1]);
        Assert.assertTrue(0.0f + margin > separation[0][1]);
        Assert.assertTrue(0.0f - margin < separation[0][2]);
        Assert.assertTrue(0.0f + margin > separation[0][2]);
        Assert.assertTrue(-7.0f - margin < separation[1][0]);
        Assert.assertTrue(-7.0f + margin > separation[1][0]);
        Assert.assertTrue(2.0f - margin < separation[1][1]);
        Assert.assertTrue(2.0f + margin > separation[1][1]);
        Assert.assertTrue(2.0f - margin < separation[1][2]);
        Assert.assertTrue(2.0f + margin > separation[1][2]);
    }

    @Test
    public void sameAngleHighLowAmplitudeTrains() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        separator.setTrain(0, transformForm(new float[] {-3.0f, -2.0f, 8.0f}, 0.5f));
        separator.setTrain(1, transformForm(new float[] {-7.0f, 2.0f, 2.0f}, 2.0f));
        separator.separateMagneticField(observation, 30);
        separator.getSeparatedMagneticField(separation);
        float margin = 0.1f;
        Assert.assertTrue(-1.5f - margin < separation[0][0]);
        Assert.assertTrue(-1.5f + margin > separation[0][0]);
        Assert.assertTrue(-1.0f - margin < separation[0][1]);
        Assert.assertTrue(-1.0f + margin > separation[0][1]);
        Assert.assertTrue(4.0f - margin < separation[0][2]);
        Assert.assertTrue(4.0f + margin > separation[0][2]);
//        Assert.assertTrue(-9.26f - margin < separation[1][0]);
//        Assert.assertTrue(-9.26f + margin > separation[1][0]);
//        Assert.assertTrue(2.64f - margin < separation[1][1]);
//        Assert.assertTrue(2.64f + margin > separation[1][1]);
//        Assert.assertTrue(2.64f - margin < separation[1][2]);
//        Assert.assertTrue(2.64f + margin > separation[1][2]);

        separator.skipIntensityNormalization(true);
        separator.separateMagneticField(observation, 30);
        separator.getSeparatedMagneticField(separation);
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

    @Test
    public void sameAngleVariousAmplitudeTrains() throws Exception {
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = {
            {0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f},
        };
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(new int[] {1, 1});
        separator.setTrain(0, transformForm(new float[] {-3.0f, -2.0f, 8.0f}, 2.5f));
        separator.setTrain(1, transformForm(new float[] {-7.0f, 2.0f, 2.0f}, 4.5f));
        separator.separateMagneticField(observation, 300);
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
