package cardgame.model;

public enum Attribute
{
    HERO() {
        @Override
        public void specialAction(Game game, int row) {

        }
    },
    BOND() {
        @Override
        public void specialAction(Game game, int row) {

        }
    },
    SUPPLY() {
        @Override
        public void specialAction(Game game, int row) {

        }
    },
    COMMANDERS_HORN() {
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.getRow(row).affect(Effect.COMMANDERS_HORN);
        }
    },
    SCOURGE() {
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.opponent.scourge();
        }
    },
    HORN() {
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.getRow(row).affect(Effect.COMMANDERS_HORN);
        }
    },
    COLD() {
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.frontRow.affect(Effect.BAD_WEATHER);
            game.currentPlayer.opponent.frontRow.affect(Effect.BAD_WEATHER);
        }
    },
    RAIN() {
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.middleRow.affect(Effect.BAD_WEATHER);
            game.currentPlayer.opponent.middleRow.affect(Effect.BAD_WEATHER);
        }
    },
    FOG() {
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.rearRow.affect(Effect.BAD_WEATHER);
            game.currentPlayer.opponent.rearRow.affect(Effect.BAD_WEATHER);
        }
    },
    GOOD_WEATHER() {
        @Override
        public void specialAction(Game game, int row) {
            for (int i = 0; i <=3; ++i) {
                game.currentPlayer.getRow(i).disaffect(Effect.BAD_WEATHER);
                game.currentPlayer.opponent.getRow(i).disaffect(Effect.BAD_WEATHER);
            }
        }
    },
    MEDIC() {
        @Override
        public void specialAction(Game game, int row) {

        }
    },
    SPY() {
        @Override
        public void specialAction(Game game, int row) throws Game.InvalidMove {
            game.getNewCard(game.currentPlayer);
            game.getNewCard(game.currentPlayer);
        }
    },
    SCORCH() {
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.opponent.scorch(1);
        }
    },
    MUSTER() {
        // warning: doesn't mix with other attributes: Player::playAll isn't intended to trigger any special actions
        // remove this warning if changing said behavior
        @Override
        public void specialAction(Game game, int row) {
            game.currentPlayer.playAll(game.currentPlayer.lastPlayedCard, row);
        }
    };

    public abstract void specialAction(Game game, int row) throws Game.InvalidMove;
}
