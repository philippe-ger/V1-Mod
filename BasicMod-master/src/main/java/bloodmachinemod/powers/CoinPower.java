package bloodmachinemod.powers;

import basemod.interfaces.CloneablePowerInterface;
import bloodmachinemod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static bloodmachinemod.BloodMachineMod.makeID;

public class CoinPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("CoinPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public CoinPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if(this.amount > 4) {
            this.amount = 4;
            updateDescription();
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        //If NORMAL (attack) damage, modify damage by this power's amount
        return type == DamageInfo.DamageType.NORMAL ? damage * (1 + 0.25F*this.amount) : damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("CoinPower")));
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play(makeID("SFX_COINTOSS"), 0F);
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new CoinPower(owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("CoinPower")));
        }

        if (this.amount >= 4) {
            this.amount = 4;
        }
    }

    @Override
    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("CoinPower")));
        }

        if (this.amount >= 4) {
            this.amount = 4;
        }
    }
}