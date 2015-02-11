package cc.redpen.model.entity;

public class Error {

    private final String sentence;

    private final int lineNum;

    private final String message;

    public Error(String sentence, int lineNum, String message) {
        this.sentence = sentence;
        this.lineNum = lineNum;
        this.message = message;
    }

    public String getSentence() {
        return sentence;
    }

    public int getLineNum() {
        return lineNum;
    }

    public String getMessage() {
        return message;
    }
}
