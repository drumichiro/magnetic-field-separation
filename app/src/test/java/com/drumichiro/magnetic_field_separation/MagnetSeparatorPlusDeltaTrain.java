package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;

import static com.drumichiro.magnet_trajectory_separation.MagnetSeparatorTestHelper.*;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorPlusDeltaTrain {
    private static final int FIELD_LINES = 3;
    private MagnetSeparator newTrainedMagnetSeparator(float[][][] trainData) {
        // trainData.length -> # of magnets.
        MagnetSeparator separator = new MagnetSeparator();
        TrainAssignment train = new TrainAssignment(trainData.length);
        separator.allocateTrainBuffer(train.lengthPerMagnet);
        // magnet, line, axises
        for (int i1=0; i1<trainData.length; ++i1) {
            for (int i2=0; i2<trainData[i1].length; ++i2) {
                float[][] trainDataBuffer = new float[1][MagnetSeparator.AXISES];
                System.arraycopy(trainData[i1][i2], 0, trainDataBuffer[0], 0, MagnetSeparator.AXISES);
                separator.setTrain(train.index[i1], i2, trainDataBuffer);
            }
        }
        return separator;
    }

    @Test
    public void assigningForwardDelta() throws Exception {
        float[][][] trainData = {
            {
                {-3.0f, -2.0f, 8.0f},
                {1.0f, 1.0f, -2.0f},
                {0.0f, 0.0f, 0.0f}
            },
            {
                {-7.0f, 2.0f, 2.0f},
                {-1.0f, 3.0f, -2.0f},
                {0.0f, 0.0f, 0.0f}
            },
        };
        int magnets = trainData.length;
        float[][] observation = {
            {-10.0f, 0.0f, 10.0f},
            {0.0f, 4.0f, -4.0f},
            {0.0f, 0.0f, 0.0f}
        };
        Assert.assertTrue(observation.length <= FIELD_LINES);
        Assert.assertTrue(trainData[0].length <= FIELD_LINES);
        float[][] separation = new float[magnets][MagnetSeparator.AXISES];
        MagnetSeparator separator = newTrainedMagnetSeparator(trainData);
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

        float integratedIntensity;
        margin = 0.001f;
        integratedIntensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 0);
        Assert.assertTrue(1.0f - margin < integratedIntensity);
        Assert.assertTrue(1.0f + margin > integratedIntensity);
        integratedIntensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 0);
        Assert.assertTrue(1.0f - margin < integratedIntensity);
        Assert.assertTrue(1.0f + margin > integratedIntensity);
        float[] intensity;
        intensity = (float[])getPrivateMagnetFieldArrayValue(separator, "intensity", 0, 0);
        Assert.assertTrue(1.0f - margin < intensity[0]);
        Assert.assertTrue(1.0f + margin > intensity[0]);
        Assert.assertTrue(1.0f - margin < intensity[1]);
        Assert.assertTrue(1.0f + margin > intensity[1]);
        Assert.assertTrue(1.0f - margin < intensity[2]);
        Assert.assertTrue(1.0f + margin > intensity[2]);
        intensity = (float[])getPrivateMagnetFieldArrayValue(separator, "intensity", 1, 0);
        Assert.assertTrue(1.0f - margin < intensity[0]);
        Assert.assertTrue(1.0f + margin > intensity[0]);
        Assert.assertTrue(1.0f - margin < intensity[1]);
        Assert.assertTrue(1.0f + margin > intensity[1]);
        Assert.assertTrue(1.0f - margin < intensity[2]);
        Assert.assertTrue(1.0f + margin > intensity[2]);
    }

    @Test
    public void assigningBackwardDelta() throws Exception {
        float[][][] trainData = {
            {
                {-3.0f, -2.0f, 8.0f},
                {0.0f, 0.0f, 0.0f},
                {-1.0f, -1.0f, 2.0f}
            },
            {
                {-7.0f, 2.0f, 2.0f},
                {0.0f, 0.0f, 0.0f},
                {1.0f, -3.0f, 2.0f}
            },
        };
        int magnets = trainData.length;
        float[][] observation = {
            {-10.0f, 0.0f, 10.0f},
            {0.0f, 0.0f, 0.0f},
            {0.0f, -4.0f, 4.0f}
        };
        Assert.assertTrue(observation.length <= FIELD_LINES);
        Assert.assertTrue(trainData[0].length <= FIELD_LINES);
        float[][] separation = new float[magnets][MagnetSeparator.AXISES];
        MagnetSeparator separator = newTrainedMagnetSeparator(trainData);
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

        float integratedIntensity;
        margin = 0.001f;
        integratedIntensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 0);
        Assert.assertTrue(1.0f - margin < integratedIntensity);
        Assert.assertTrue(1.0f + margin > integratedIntensity);
        integratedIntensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 0);
        Assert.assertTrue(1.0f - margin < integratedIntensity);
        Assert.assertTrue(1.0f + margin > integratedIntensity);
        float[] intensity;
        intensity = (float[])getPrivateMagnetFieldArrayValue(separator, "intensity", 0, 0);
        Assert.assertTrue(1.0f - margin < intensity[0]);
        Assert.assertTrue(1.0f + margin > intensity[0]);
        Assert.assertTrue(1.0f - margin < intensity[1]);
        Assert.assertTrue(1.0f + margin > intensity[1]);
        Assert.assertTrue(1.0f - margin < intensity[2]);
        Assert.assertTrue(1.0f + margin > intensity[2]);
        intensity = (float[])getPrivateMagnetFieldArrayValue(separator, "intensity", 1, 0);
        Assert.assertTrue(1.0f - margin < intensity[0]);
        Assert.assertTrue(1.0f + margin > intensity[0]);
        Assert.assertTrue(1.0f - margin < intensity[1]);
        Assert.assertTrue(1.0f + margin > intensity[1]);
        Assert.assertTrue(1.0f - margin < intensity[2]);
        Assert.assertTrue(1.0f + margin > intensity[2]);
    }

    @Test
    public void assigningBothDelta() throws Exception {
        float[][][] trainData = {
            {
                {-3.0f, -2.0f, 8.0f},
                {1.0f, 1.0f, -2.0f},
                {-1.0f, -1.0f, 2.0f}
            },
            {
                {-7.0f, 2.0f, 2.0f},
                {-1.0f, 3.0f, -2.0f},
                {1.0f, -3.0f, 2.0f}
            },
        };
        int magnets = trainData.length;
        float[][] observation = {
            {-10.0f, 0.0f, 10.0f},
            {0.0f, 4.0f, -4.0f},
            {0.0f, -4.0f, 4.0f}
        };
        Assert.assertTrue(observation.length <= FIELD_LINES);
        Assert.assertTrue(trainData[0].length <= FIELD_LINES);
        float[][] separation = new float[magnets][MagnetSeparator.AXISES];
        MagnetSeparator separator = newTrainedMagnetSeparator(trainData);
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

        float integratedIntensity;
        margin = 0.001f;
        integratedIntensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 0);
        Assert.assertTrue(1.0f - margin < integratedIntensity);
        Assert.assertTrue(1.0f + margin > integratedIntensity);
        integratedIntensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 0);
        Assert.assertTrue(1.0f - margin < integratedIntensity);
        Assert.assertTrue(1.0f + margin > integratedIntensity);
        float[] intensity;
        intensity = (float[])getPrivateMagnetFieldArrayValue(separator, "intensity", 0, 0);
        Assert.assertTrue(1.0f - margin < intensity[0]);
        Assert.assertTrue(1.0f + margin > intensity[0]);
        Assert.assertTrue(1.0f - margin < intensity[1]);
        Assert.assertTrue(1.0f + margin > intensity[1]);
        Assert.assertTrue(1.0f - margin < intensity[2]);
        Assert.assertTrue(1.0f + margin > intensity[2]);
        intensity = (float[])getPrivateMagnetFieldArrayValue(separator, "intensity", 1, 0);
        Assert.assertTrue(1.0f - margin < intensity[0]);
        Assert.assertTrue(1.0f + margin > intensity[0]);
        Assert.assertTrue(1.0f - margin < intensity[1]);
        Assert.assertTrue(1.0f + margin > intensity[1]);
        Assert.assertTrue(1.0f - margin < intensity[2]);
        Assert.assertTrue(1.0f + margin > intensity[2]);
    }
}
