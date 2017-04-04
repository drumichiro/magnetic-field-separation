package com.drumichiro.magnet_trajectory_separation;

import junit.framework.Assert;

import org.junit.Test;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MagnetSeparatorExceptionHandling {

    @Test
    public void irregularLength() throws Exception {
        int separations = 2;
        MagnetSeparator separator = new MagnetSeparator();
        try {
            separator.allocateTrainBuffer(new int[] {1, 1});
            separator.setTrain(-1, new float[1][MagnetSeparator.AXISES]);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }
        try {
            separator.allocateTrainBuffer(new int[] {1, 1});
            separator.setTrain(separations, new float[1][MagnetSeparator.AXISES]);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }

        try {
            separator.allocateTrainBuffer(new int[] {1, 1});
            separator.setTrain(0, new float[][] { {1.0f, -1.0f}, });
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }
        try {
            separator.allocateTrainBuffer(new int[] {1, 1});
            separator.setTrain(0, new float[][] { {1.0f, -1.0f, 0.0f, 1.0f}, });
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }

        try {
            float[] observation = new float[MagnetSeparator.AXISES];
            float[][] separation = new float[separations - 1][MagnetSeparator.AXISES];
            separator.separateMagneticField(observation, 0);
            separator.getSeparatedMagneticField(separation);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }
        try {
            float[] observation = new float[MagnetSeparator.AXISES];
            float[][] separation = new float[separations + 1][MagnetSeparator.AXISES];
            separator.separateMagneticField(observation, 0);
            separator.getSeparatedMagneticField(separation);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }

        try {
            float[] observation = new float[MagnetSeparator.AXISES - 1];
            float[][] separation = new float[separations][MagnetSeparator.AXISES];
            separator.separateMagneticField(observation, 0);
            separator.getSeparatedMagneticField(separation);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }
        try {
            float[] observation = new float[MagnetSeparator.AXISES + 1];
            float[][] separation = new float[separations][MagnetSeparator.AXISES];
            separator.separateMagneticField(observation, 0);
            separator.getSeparatedMagneticField(separation);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }

        try {
            float[] observation = new float[MagnetSeparator.AXISES];
            float[][] separation = new float[separations][MagnetSeparator.AXISES - 1];
            separator.separateMagneticField(observation, 0);
            separator.getSeparatedMagneticField(separation);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }
        try {
            float[] observation = new float[MagnetSeparator.AXISES];
            float[][] separation = new float[separations][MagnetSeparator.AXISES + 1];
            separator.separateMagneticField(observation, 0);
            separator.getSeparatedMagneticField(separation);
            throw new Exception();
        }
        catch (AssertionError e) {
            // No check.
        }
    }
}
