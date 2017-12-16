package cardgame.server.communication;

public enum Action {
    PUT_CARD,
    PASS;

    public final String RESPONSE_NAME;
    public final String COMMAND_NAME;

    Action() {
        StringBuilder sb = new StringBuilder();
        String[] words = this.toString().split("_");

        for (String word : words) {
            sb.append(word.charAt(0)).append(word.substring(1).toLowerCase());
        }
        RESPONSE_NAME = sb.toString();

        COMMAND_NAME = this.toString().toLowerCase();
    }
}
