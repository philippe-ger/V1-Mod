package bloodmachinemod.powers;

import basemod.interfaces.CloneablePowerInterface;
import bloodmachinemod.actions.IntoTheFireAction;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

import static bloodmachinemod.BloodMachineMod.makeID;

public class IntoTheFirePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("IntoTheFirePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public IntoTheFirePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if(this.amount > 1) {
            this.amount = 1;
        }
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        int count = 0;
        Iterator<AbstractCard> var2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.type == AbstractCard.CardType.ATTACK) {
                ++count;
            }
        }

        if (count == 1) {
            addToBot(new AddTemporaryHPAction(this.owner,this.owner, (int) damageAmount));
        }
        super.onAttack(info, damageAmount, target);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new IntoTheFirePower(owner, amount);
    }
}