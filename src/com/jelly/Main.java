package com.jelly;

public class Main {

    static String[] studentNames = new String[5];
    static double[] studentScores = new double[5];
    final static double[] POINTS_POSSIBLE = {100.00, 100.00, 100.00, 100.00, 100.00};

    private static void fillNames(String[] names) { // Copy given array of names into Main#studentNames.
        System.arraycopy(names, 0, studentNames, 0, studentNames.length);
    }

    private static void fillScores(double[] scores) { // Using an int index instead of a byte (since schools often have far more than 127 students), fill the score percentages (rounded to 2 decimal places) into Main#studentPoints.
        for (int i = 0; i < studentScores.length; i++) studentScores[i] = Math.round((scores[i] / POINTS_POSSIBLE[i] * 100.00) * 100.00) / 100.00;
    }

    private static String assignGrade(double score) {
        if (score >= 98) return "A+";
        else if (score >= 93) return "A";
        else if (score >= 90) return "A-";
        else if (score >= 87) return "B+";
        else if (score >= 83) return "B";
        else if (score >= 80) return "B-";
        else if (score >= 77) return "C+";
        else if (score >= 73) return "C";
        else if (score >= 70) return "C-";
        else if (score >= 67) return "D+";
        else if (score >= 63) return "D";
        else if (score >= 60) return "D-";
        else return "F";
    }

    public static void main(String[] args) {

        // Data input.
        fillNames(new String[]{"Elias Barbera", "Justy Pickering", "Bram Andrewson", "Melvin Blanchard", "Joline Outterridge"}); // Input student names here.
        fillScores(new double[]{9.0, 68.0, 83.5, 45.95, 105.5555555}); // Input equivalent student scores here.
        String header1 = "Name", header2 = "Score", header3 = "Grade"; // Input preferred column headers here.

        // Set minimum values of column widths to the length of each header.
        byte nameWidth = (byte) header1.length(),
                scoreWidth = (byte) header2.length(),
                gradeWidth = (byte) header3.length();

        for (String s : studentNames) if (s.length() > nameWidth) nameWidth = (byte) s.length(); // Get maximum length of given student names.
        for (double s : studentScores) if (String.valueOf(s).length() + 1 > scoreWidth) scoreWidth = (byte) (String.valueOf(s).length() + 1); // Get maximum length of given student scores, since it's possible for the values to be longer than the header.

        StringBuilder output = new StringBuilder();

        // Hope you don't mind me making this exam a little more fun for myself by practicing box-building ~<3
        { // Build table header. Putting it in a container so that it can be minimiseed in the IDE, since this isn't part of the grading. I'd use a function for this, but the question implies I can only have 3 :(
            output.append("┌─");
            for (byte i = 0; i < nameWidth; i++) output.append('─');
            output.append("─┬─");
            for (byte i = 0; i < scoreWidth; i++) output.append('─');
            output.append("─┬─");
            for (byte i = 0; i < gradeWidth; i++) output.append('─');
            output.append("─┐\n│ ")
                    .append(String.format("%-" + nameWidth + "s", header1)).append(" │ ")
                    .append(String.format("%-" + scoreWidth + "s", header2)).append(" │ ")
                    .append(String.format("%-" + gradeWidth + "s", header3)).append(" │\n")
            .append("├─");
            for (byte i = 0; i < nameWidth; i++) output.append('─');
            output.append("─┼─");
            for (byte i = 0; i < scoreWidth; i++) output.append('─');
            output.append("─┼─");
            for (byte i = 0; i < gradeWidth; i++) output.append('─');
            output.append("─┤\n");
        }

        double grade;
        for (byte i = 0; i < studentNames.length; i++) output.append("│ ")
            .append(String.format("%-" + nameWidth + "s", studentNames[i])).append(" │ ")
            .append(String.format("%-" + scoreWidth + "s", (grade = studentScores[i]) + "%")).append(" │ ")
            .append(String.format("%-" + gradeWidth + "s", assignGrade(grade))).append(" │\n");

        { // Build table footer. Putting it in a container so that it can be minimiseed in the IDE, since this isn't part of the grading. I'd use a function for this, but the question implies I can only have 3 :(
            output.append("└─");
            for (byte i = 0; i < nameWidth; i++) output.append('─');
            output.append("─┴─");
            for (byte i = 0; i < scoreWidth; i++) output.append('─');
            output.append("─┴─");
            for (byte i = 0; i < gradeWidth; i++) output.append('─');
            output.append("─┘");
        }

        System.out.print(output.toString());
    }

}
