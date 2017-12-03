import java.util.ArrayList;
import java.util.Arrays;

enum CardType {
    BASIC(new Attribute[]{}, "BASIC") {
        @Override
        public void specialAction(Player player, int row) {

        }
    },
    SCOURGE(new Attribute[]{}, "SCOURGE") {
        @Override
        public void specialAction(Player player, int row) {
            player.opponent.scourge();
        }
    },
    HORN(new Attribute[]{Attribute.COMMANDERS_HORN}, "HORN") {
        @Override
        public void specialAction(Player player, int row) {
            player.getRow(row).affect(Effect.COMMANDERS_HORN);
        }
    },
    COLD(new Attribute[]{}, "COLD") {
        @Override
        public void specialAction(Player player, int row) {
            player.frontRow.affect(Effect.BAD_WEATHER);
            player.opponent.frontRow.affect(Effect.BAD_WEATHER);
        }
    },
    RAIN(new Attribute[]{}, "RAIN") {
        @Override
        public void specialAction(Player player, int row) {
            player.middleRow.affect(Effect.BAD_WEATHER);
            player.opponent.middleRow.affect(Effect.BAD_WEATHER);
        }
    },
    FOG(new Attribute[]{}, "FOG") {
        @Override
        public void specialAction(Player player, int row) {
            player.rearRow.affect(Effect.BAD_WEATHER);
            player.opponent.rearRow.affect(Effect.BAD_WEATHER);
        }
    },
    SUPPLY(new Attribute[]{Attribute.SUPPLY}, "SUPPLY") {
        @Override
        public void specialAction(Player player, int row) {

        }
    },
    GOOD_WEATHER(new Attribute[]{}, "GOOD_WEATHER") {
        @Override
        public void specialAction(Player player, int row) {
            for (int i = 0; i <=3; ++i) {
                player.getRow(i).disaffect(Effect.BAD_WEATHER);
                player.opponent.getRow(i).disaffect(Effect.BAD_WEATHER);
            }
        }
    };

    public ArrayList<Attribute> attributes;
    public String name;

    public abstract void specialAction(Player player, int row);

    public Boolean is(Attribute attribute) {
        return attributes.contains(attribute);
    }

    CardType(Attribute[] a, String s) {
        attributes = new ArrayList<>(Arrays.asList(a));
        name = s;
    }
}
