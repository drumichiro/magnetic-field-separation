package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;

import static com.drumichiro.magnet_trajectory_separation.MagnetSeparatorTestHelper.*;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorMultiTrain {

    private MagnetSeparator newTrainedMagnetSeparator(float[][] trainData, int trainsPerMagnet) {
        int[] trainsPerMagnetArray = new int[trainData.length];
        Arrays.fill(trainsPerMagnetArray, trainsPerMagnet);
        return newTrainedMagnetSeparator(trainData, trainsPerMagnetArray);
    }

    private MagnetSeparator newTrainedMagnetSeparator(float[][] trainData, int[] trainsPerMagnet) {
        // trainData.length -> # of magnets.
        MagnetSeparator separator = new MagnetSeparator();
        separator.allocateTrainBuffer(trainsPerMagnet);
        for (int i1=0; i1<trainData.length; ++i1) {
            float[][] trainDataBuffer = new float[trainsPerMagnet[i1]][];
            for (int i2=0; i2<trainDataBuffer.length; ++i2) {
                trainDataBuffer[i2] = trainData[i1];
            }
            separator.setTrain(i1, trainDataBuffer);
        }
        return separator;
    }

    @Test
    public void assigningTwoTrainsForEachMagnet() throws Exception {
        float[][] trainData = {
            {-3.0f, -2.0f, 8.0f},
            {-7.0f, 2.0f, 2.0f},
        };
        int magnets = trainData.length;
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = new float[magnets][observation.length];
        MagnetSeparator separator = newTrainedMagnetSeparator(trainData, 2);
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

        float intensity;
        margin = 0.001f;
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 0);
        Assert.assertTrue(0.5f - margin < intensity);
        Assert.assertTrue(0.5f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 1);
        Assert.assertTrue(0.5f - margin < intensity);
        Assert.assertTrue(0.5f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 0);
        Assert.assertTrue(0.5f - margin < intensity);
        Assert.assertTrue(0.5f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 1);
        Assert.assertTrue(0.5f - margin < intensity);
        Assert.assertTrue(0.5f + margin > intensity);
    }

    @Test
    public void assigningSevenTrainsForEachMagnet() throws Exception {
        float[][] trainData = {
            {-3.0f, -2.0f, 8.0f},
            {-7.0f, 2.0f, 2.0f},
        };
        int magnets = trainData.length;
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = new float[magnets][observation.length];
        MagnetSeparator separator = newTrainedMagnetSeparator(trainData, 7);
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

        float intensity;
        margin = 0.001f;
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 0);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 1);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 2);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 3);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 4);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 5);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 6);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 0);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 1);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 2);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 3);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 4);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 5);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 6);
        Assert.assertTrue(0.143f - margin < intensity);
        Assert.assertTrue(0.143f + margin > intensity);
    }

    @Test
    public void assigningOneTrainAndSeveralTrainsRespectively() throws Exception {
        float[][] trainData = {
            {-3.0f, -2.0f, 8.0f},
            {-7.0f, 2.0f, 2.0f},
        };
        int magnets = trainData.length;
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = new float[magnets][observation.length];
        MagnetSeparator separator = newTrainedMagnetSeparator(trainData, new int[] {1, 4});
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

        float intensity;
        margin = 0.001f;
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 0);
        Assert.assertTrue(1.0f - margin < intensity);
        Assert.assertTrue(1.0f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 0);
        Assert.assertTrue(0.25f - margin < intensity);
        Assert.assertTrue(0.25f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 1);
        Assert.assertTrue(0.25f - margin < intensity);
        Assert.assertTrue(0.25f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 2);
        Assert.assertTrue(0.25f - margin < intensity);
        Assert.assertTrue(0.25f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 3);
        Assert.assertTrue(0.25f - margin < intensity);
        Assert.assertTrue(0.25f + margin > intensity);
    }

    @Test
    public void assigningSeveralTrainsAndSeveralTrainsRespectively() throws Exception {
        float[][] trainData = {
            {-3.0f, -2.0f, 8.0f},
            {-7.0f, 2.0f, 2.0f},
        };
        int magnets = trainData.length;
        float[] observation = {-10.0f, 0.0f, 10.0f};
        float[][] separation = new float[magnets][observation.length];
        MagnetSeparator separator = newTrainedMagnetSeparator(trainData, new int[] {6, 3});
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

        float intensity;
        margin = 0.001f;
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 0);
        Assert.assertTrue(0.167f - margin < intensity);
        Assert.assertTrue(0.167f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 1);
        Assert.assertTrue(0.167f - margin < intensity);
        Assert.assertTrue(0.167f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 2);
        Assert.assertTrue(0.167f - margin < intensity);
        Assert.assertTrue(0.167f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 3);
        Assert.assertTrue(0.167f - margin < intensity);
        Assert.assertTrue(0.167f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 4);
        Assert.assertTrue(0.167f - margin < intensity);
        Assert.assertTrue(0.167f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 0, 5);
        Assert.assertTrue(0.167f - margin < intensity);
        Assert.assertTrue(0.167f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 0);
        Assert.assertTrue(0.333f - margin < intensity);
        Assert.assertTrue(0.333f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 1);
        Assert.assertTrue(0.333f - margin < intensity);
        Assert.assertTrue(0.333f + margin > intensity);
        intensity = (float)getPrivateMagnetFieldArrayValue(separator, "integratedIntensity", 1, 2);
        Assert.assertTrue(0.333f - margin < intensity);
        Assert.assertTrue(0.333f + margin > intensity);
    }
}
