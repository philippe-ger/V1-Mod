package bloodmachinemod.cards.rare;

import bloodmachinemod.cards.BaseCard;
import bloodmachinemod.character.BloodMachine;
import bloodmachinemod.powers.AirbornePower;
import bloodmachinemod.powers.CoinPower;
import bloodmachinemod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Ultraskill extends BaseCard {
    public static final String ID = makeID(Ultraskill.class.getSimpleName());
    private static final CardStats info = new CardStats(
            BloodMachine.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0
    );

    private static final int MAGIC_NUM = 1;
    private static final int UPG_MAGIC= 1;

    public Ultraskill() {
        super(ID, info);
        setMagic(MAGIC_NUM,UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Jump
        if(!p.hasPower(makeID("AirbornePower"))) {
            addToBot(new ApplyPowerAction(p,p,new AirbornePower(p,1)));
        }
        else {
            addToBot(new DrawCardAction(p,1,false));
        }
        //Toss !M!
        addToBot(new ApplyPowerAction(p,p,new CoinPower(p,this.magicNumber)));
        //Gain [E]
        addToBot(new GainEnergyAction(1));
        //Draw !M!
        addToBot(new DrawCardAction(p,this.magicNumber,false));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Ultraskill();
    }
}