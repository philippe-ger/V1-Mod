package bloodmachinemod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static bloodmachinemod.BloodMachineMod.makeID;

public class VersusPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("VersusPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    private int MAGIC_NUMBER = 0;
    public VersusPower(AbstractCreature owner, int amount, int magic) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.MAGIC_NUMBER = magic;
        if(this.amount > 1) {
            this.amount = 1;
            updateDescription();
        }
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        //If NORMAL (attack) damage, modify damage by this power's amount
        return type == DamageInfo.DamageType.NORMAL ? damage * (1 - (MAGIC_NUMBER * 0.01F)) : damage;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + MAGIC_NUMBER + DESCRIPTIONS[2];
    }


    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new VersusPower(owner, amount,MAGIC_NUMBER);
    }

}