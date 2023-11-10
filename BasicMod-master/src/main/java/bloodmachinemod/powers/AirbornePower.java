package bloodmachinemod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static bloodmachinemod.BloodMachineMod.makeID;

public class AirbornePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("AirbornePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public AirbornePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if(owner.isPlayer) {
            this.type = PowerType.BUFF;
        }
        else {
            this.type = PowerType.DEBUFF;
        }
        updateDescription();
    }
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        //If NORMAL (attack) damage, modify damage by this power's amount
        if(this.owner.isPlayer) {
            return type == DamageInfo.DamageType.NORMAL ? damage - 3 : damage;
        }
        else {
            return type == DamageInfo.DamageType.NORMAL ? damage * (1.5F) : damage;
        }
    }

    public void updateDescription() {
        if(this.owner.isPlayer)
            this.description = DESCRIPTIONS[0];
        else
            this.description = DESCRIPTIONS[1];
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play(makeID("SFX_JUMP"), 0F);
    }

    public void atEndOfRound() {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new AirbornePower(owner, amount);
    }
}