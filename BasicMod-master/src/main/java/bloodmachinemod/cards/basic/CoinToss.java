package bloodmachinemod.cards.basic;

import bloodmachinemod.cards.BaseCard;
import bloodmachinemod.character.BloodMachine;
import bloodmachinemod.powers.CoinPower;
import bloodmachinemod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;

public class CoinToss extends BaseCard {
    public static final String ID = makeID(CoinToss.class.getSimpleName());
    private static final CardStats info = new CardStats(
            BloodMachine.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            0
    );

    private static final int MAGIC_NUM = 1;
    private static final int UPG_MAGIC= 1;

    public CoinToss() {
        super(ID, info);
        setMagic(MAGIC_NUM,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p,p,new CoinPower(p,this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CoinToss();
    }
}