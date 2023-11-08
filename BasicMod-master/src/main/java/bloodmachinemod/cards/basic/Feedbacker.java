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

public class Feedbacker extends BaseCard {
    public static final String ID = makeID(Feedbacker.class.getSimpleName());
    private static final CardStats info = new CardStats(
            BloodMachine.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 10;

    public Feedbacker() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int ricochet = 0;
        if(p.hasPower(makeID("CoinPower"))) {
            ricochet = p.getPower(makeID("CoinPower")).amount;
        }
        if(ricochet > 0) {
            addToBot(new ApplyPowerAction(p,p,new CoinPower(p,ricochet)));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Feedbacker();
    }
}