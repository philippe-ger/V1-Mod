package bloodmachinemod.relics.starter;

import bloodmachinemod.character.BloodMachine;
import bloodmachinemod.relics.BaseRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static bloodmachinemod.BloodMachineMod.makeID;

public class AbsorbentArmor extends BaseRelic {
    private static final String NAME = "AbsorbentArmor"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public AbsorbentArmor() {
        super(ID, NAME, BloodMachine.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if(targetCard.type == AbstractCard.CardType.ATTACK) {
            int count = 0;
            Iterator<AbstractCard> var = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();

            while(var.hasNext()) {
                AbstractCard c = (AbstractCard)var.next();
                if (c.type == AbstractCard.CardType.ATTACK) {
                    ++count;
                }
            }
            if(count > 4) {
                count = 4;
            }
            addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,count));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}