package bloodmachinemod.cards.basic;

import bloodmachinemod.cards.BaseCard;
import bloodmachinemod.character.BloodMachine;
import bloodmachinemod.powers.AirbornePower;
import bloodmachinemod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Jump extends BaseCard {
    public static final String ID = makeID(Jump.class.getSimpleName());
    private static final CardStats info = new CardStats(
            BloodMachine.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            1
    );

    private static final int MAGIC_NUM = 0;
    private static final int UPG_MAGIC= 1;

    public Jump() {
        super(ID, info);
        setMagic(MAGIC_NUM,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            if(!p.hasPower(makeID("AirbornePower"))) {
                addToBot(new ApplyPowerAction(p,p,new AirbornePower(p,1)));
            }
            else {
                addToBot(new DrawCardAction(p,1,false));
            }
            if(this.magicNumber > 0) {
                addToBot(new DrawCardAction(p,this.magicNumber,false));
            }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Jump();
    }
}