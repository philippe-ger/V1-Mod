package bloodmachinemod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static bloodmachinemod.BloodMachineMod.makeID;

public class CyberGrindPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("CyberGrindPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    private int MAGIC_NUMBER = 0;
    public CyberGrindPower(AbstractCreature owner, int amount, int magic) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.MAGIC_NUMBER = magic;
        if(this.amount > 1) {
            this.amount = 1;
        }
        updateDescription();
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new CoinPower(AbstractDungeon.player,MAGIC_NUMBER)));
        if(!AbstractDungeon.player.hasPower(makeID("AirbornePower"))) {
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new AirbornePower(AbstractDungeon.player,1)));
        }
        else {
            addToBot(new DrawCardAction(AbstractDungeon.player,1,false));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + MAGIC_NUMBER +DESCRIPTIONS[1];
    }


    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new CyberGrindPower(owner, amount, MAGIC_NUMBER);
    }

}