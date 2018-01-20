package cardgame.model;

public enum Attribute
{
    HERO() {
        @Override
        public void specialAction(Player player, int row) {

        }
    },
    BOND() {
        @Override
        public void specialAction(Player player, int row) {

        }
    },
    SUPPLY() {
        @Override
        public void specialAction(Player player, int row) {

        }
    },
    COMMANDERS_HORN() {
        @Override
        public void specialAction(Player player, int row) {
            player.getRow(row).affect(Effect.COMMANDERS_HORN);
        }
    },
    SCOURGE() {
        @Override
        public void specialAction(Player player, int row) {
            player.opponent.scourge();
        }
    },
    HORN() {
        @Override
        public void specialAction(Player player, int row) {
            player.getRow(row).affect(Effect.COMMANDERS_HORN);
        }
    },
    COLD() {
        @Override
        public void specialAction(Player player, int row) {
            player.frontRow.affect(Effect.BAD_WEATHER);
            player.opponent.frontRow.affect(Effect.BAD_WEATHER);
        }
    },
    RAIN() {
        @Override
        public void specialAction(Player player, int row) {
            player.middleRow.affect(Effect.BAD_WEATHER);
            player.opponent.middleRow.affect(Effect.BAD_WEATHER);
        }
    },
    FOG() {
        @Override
        public void specialAction(Player player, int row) {
            player.rearRow.affect(Effect.BAD_WEATHER);
            player.opponent.rearRow.affect(Effect.BAD_WEATHER);
        }
    },
    GOOD_WEATHER() {
        @Override
        public void specialAction(Player player, int row) {
            for (int i = 0; i <=3; ++i) {
                player.getRow(i).disaffect(Effect.BAD_WEATHER);
                player.opponent.getRow(i).disaffect(Effect.BAD_WEATHER);
            }
        }
    },
    MEDIC() {
        @Override
        public void specialAction(Player player, int row) {
            player.reviveRandomCard();
        }
    },
    SPY() {
        @Override
        public void specialAction(Player player, int row) throws Game.InvalidMove {
            player.pullRandomCard();
            player.pullRandomCard();
        }
    },
    SCORCH() {
        @Override
        public void specialAction(Player player, int row) {
            player.opponent.scorch(1);
        }
    },
    MUSTER() {
        // warning: doesn't mix with other attributes: Player::playAll isn't intended to trigger any special actions
        // remove this warning if changing said behavior
        @Override
        public void specialAction(Player player, int row) {
            player.playAll(player.lastPlayedCard, row);
        }
    };

    public abstract void specialAction(Player player, int row) throws Game.InvalidMove;
}
