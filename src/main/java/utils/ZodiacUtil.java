package utils;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.Zodiac;

import org.json.JSONException;

public class ZodiacUtil {

	private static final String zodiac= "{\r\n" + 
			"\"leo\":\"Lion is considered the ruler of the jungle, so also you are the leader of your own world; you exhibit impeccable bravery, superiority and sovereignty. You are expected to achieve extraordinary feats. You have the strength of character to take command of any situation and your presence commands respect and attention. And just like a king, you have a big heart, you are generous and sensitive to others’ feelings. But again, you are quite egotistical and it’s quite easy to flatter and take advantage of you. At the same time, you tend to be quite self-congratulatory and boastful and at the same time dominating, courting resentment from your peers. Leo is all about ambition and compassion; and at the same time headstrong and a little corrupted by power. But it equally characteristic of you to be contemplative and moody, especially when you are with yourself.\",\r\n" + 
			"\"aries\":\"You are aggressive, the kind of person who takes problems by the headlocks. You are as fearless as the Gods and enthusiastic and fervent to the extreme. Your sex drive is very strong and you are quite aptly, extrovert. But the drawback is in terms of your egotism. Even though you are the leader in your peer group, you lack the wisdom required in a leader. You might also show attributes of blunt and roguish behavior and lack of pragmatism while dealing with people. However, having strong emotions and being courageous and forceful, you forge your way ahead in precarious situations. So when a new endeavor is to be undertaken, you will be the crucial person. Your approach with opposite sex is altogether aggressive and straight-on, like your approach towards life itself. You have an exceptional exuberance and stamina, and this probably originates from your quality of being your own master, but this also leads to the lack of style and grace. You don’t talk a lot or try to justify, you just assert your supremacy and work. Now you can decide for yourself how good this can be for your leadership. You are not very social, but you are quite helpful and even gullible. May be that is why people try to take undue advantage of you and even become successful.\",\r\n" + 
			"\"taurus\":\"Taurus is the sign of manhood, strength, stamina, perseverance and potency. You are quite calm and serene, but when bothered, you will turn wicked and dangerous. You also like your life to be stable and not perturbed with sudden changes. You have an air of reliability and sensibility about that makes you quite valuable. You have an inherent urge to create and build stuff that is why you are impeccable when it comes to managing money, men and tasks. You are quite attracted by the luxuries that come with money and are ready to work for them. You also have a hospitable, and a epicurean side to your personality and have a tremendous urge for physical pleasure. You are consistent with whatever they do or say, as you don’t have a tendency to change opinion; your temper is also quite distinctive – short burst with no prior caution. Even Taureans are quite moody, and like to keep themselves immersed in their work. You are the ones who get restless and mischievous when idle. You do care a lot about your family and friends. There is a double world within you, a normal world and another spiritual and enigmatic to the extreme. Qualities that make you lucky are realistic, reliable, openhanded, and arty. Qualities that hinder your success are possessiveness, stubbornness, lethargy, and self-indulgence.\",\r\n" + 
			"\"gemini\":\"Gemini signifies the dual edged nature that you possess. You have a great mind that does a lot more than calculate and judge. You are inquisitive and new and unique ideas come to you quite naturally, and these do not lack any of the style and elegance. Your twin edge makes you are quite determined and stationary but capricious from time to time, you are sincere in your relationship with your sweetheart but at the same time have a tendency to flirt with other women. So, you are quite swift and take command over the situation in no time, but its difficult for you to stay on top, or maintain the lead. You also have a tendency to reach out to people; this is why you cannot live without communicating. You are quite astute and opportunistic and don’t miss a chance to make fast money. Corresponding to this, you also spend with quite ease. You are quite smart and funny and this attracts people to you all the more, but you need to be more objective and focused towards your goal. You seem to be quite susceptible to changes in your mood, and this will only lead to greater loss of the sense of purpose. Try to gain control of yourself. Qualities that make you lucky are wittiness, brilliancy, cheery, and gay. Qualities that hinder your success are messy, happy-go-lucky, cruelty, and incompatible.\",\r\n" + 
			"\"cancer\":\"Cancerians are considered to be quite unsophisticated from the inside and prone to move around in search of the right opportunity. It is also a widely accepted notion that they are sensitive and protective. They don’t find it difficult to blend in the environment, and at the same time, are also quite attached to their family. But there are quite many other facets to your personality. You have special love for the spotlight, and the desire to be admired and loved is very dominant in your character. You are very touchy, and get emotional a lot more often than others. You sense of purpose for everything you do, and you are ready to endure anything if you feel the end result is worth it. You will move in all directions to reach you destination, and this makes things possible for you that no other person around you can manage to pull off. This also increases your money making opportunities, and you might well make your way into large money-making firms. You have an innate feeling of insecurity and timidity, and this prevents you from taking risks and thereby inhibits your growth. You have strong likes and dislikes and preconceived notions. Talking about your sex drive, you clearly exhibit your intrinsic craving for proximity. You need intimacy from your mate, failing which you develop a wall between you and your mate. To top it all, you have a fascination for money and all the luxuries that it can bring to your feet. Qualities that make you lucky are understanding, imaginative, desire to success, and trustworthiness. Qualities that hinder your success are moodiness, pride, pessimism, and tendency to get hurt easily.\",\r\n" + 
			"\"virgo\":\"You are thought of as quite shy and introvert, and even quite naive. Your personality and composure is virginal in the sense that you are passive and still don’t let your personality and approach get affected by others. The positives are that you are very organized, meticulous and systematic and don’t like cluttering and irregularity. You perform your tasks with a machine like planning and accuracy and this might get on others’ nerves. You are very down to earth and are acknowledged for your ability of making people feel comfortable and at home in your company. This is in close association with the fact that you possess an ant-like sincerity and solemnity, and at the same time don’t bother about who gets the credit or the spotlight. You might also possess that nervousness and apprehension that is quite characteristic to people who work hard and want their results too bad. You are not that reserved against sex but take it in a very pure sense. You fancy yourself for being too wise in terms of managing money and making friends, that is the reason why you tend to be a little stingy and at the same time, a bit too analytical and judgmental of the people around you, even your close friends and family. Qualities that make you lucky are systematic, rational, communicative, and discriminating. Qualities that hinder your success are unbending, indifference and argumentative.\",\r\n" + 
			"\"libra\":\"You, being a Libran, have a characteristic balance in your personality, work and also strive for this equality and harmony in your personal and professional equations. You tend to compare and analyze to make the choice in almost every sort of situation. This also shows that you are apt for giving justice, whatever that might mean to you. These are the symbolic attributes; other than these, you have many other qualities that are not as highlighted. You are intelligent and your strife and tussle is in the inner direction; you challenge yourself and try to achieve whatever you want, so any intellectual feet is expected more from you than from anyone else. But the balancing and comparing attitude of yours keeps you aloof from Quality Company; you might lose even the few friends you make in due course. You can be quite influential and tactful in your interactions with people but at the same time indecisive and yielding. You don’t possess the power to say ‘no’ and this may lead to some pretty unnerving and awkward situations for you. You are good too many things and find it difficult to stick to one and do it for a long time. You would prefer a job where you are in contact with people rather than a desk job, and even at that you are quite easygoing and don’t want to sweat out. Qualities that make you lucky are charismatic, intellectual, awareness, and evenhanded. Qualities that hinder your success are easygoing, insecure, lazy, and self-centered.\",\r\n" + 
			"\"scorpio\":\"Being a Scorpio, you are quite shrewd and astute and even intensely revengeful in cases, you are not the right person to cross. You are the symbol of courage and power, and this is certainly accompanied by foresight, intelligence and power, along the freedom that this power provides. Your vision gives you the power to see farther than your comrades and opponents. And this provides you with the power to strike down those you oppose you, figuratively. Adventure lures and you and finds you wherever you are, and this intrinsically makes you a leader, as you dare to tread where no one else dares to go. You have a great stamina, and as I talked about, you are a deadly adversary. You expect total loyalty and sincerity from your friends, as you give the same to them and you are not that easily deceived, because of your superior vision. You are brilliant and tactical in your business, but run the risk of appearing, and even being deceitful and surreptitious. You are quite dedicated and sensible, but still in some known or unknown way, you tend to yourself in jeopardy. But still, you will succeed a lot in life, and also make a lot many enemies in the process, but they are innocuous and only cause anxiety. You receive cooperation from your coworkers and subordinates and satisfaction from your family. Qualities that make you lucky are will power, positive, political, and courageous. Qualities that hinder your success are possessiveness, dominating, jealous, and cold-blooded.\",\r\n" + 
			"\"sagittarius\":\"The centaur that depicts your zodiac symbol represents the partial animal nature in you, and also your spiritual and philosophical craving for the all important truths of life. You are thought of as brave, enterprising and foresighted individuals. But still, you have an uncivilized taint to your personality and somehow tend to get into fights and arguments. Your adventurous character is backed by a great inner enthusiasm and optimism which shows in your interactions and work. You tend to be a little irresponsible in your dealings and interactions and don’t apply your farsightedness to the repercussions of your own behavior. You have an impeccable honesty and this only instills in you a feeling that you are not answerable to anyone, and need not be afraid of anyone; this tends to make you a little outspoken too. You have a huge self-respect that works in a inward sense and makes you quite independent and straight-forward. You also have a generous and nature-loving side to yourself that is not quite noticed. Your idealistic nature makes you very frank and fearless, but it also makes you tactless, unsophisticated and inconsistent in your work. Also, your impatience and boastful aggression help the situation. Perhaps a little more patience and sensibility will help you in your professional and domestic spheres. Qualities that make you lucky are fearlessness, self-governing, sharpness, and gutsy. Qualities that hinder your success are aggressiveness, full of yourself, over-confidence, and contradictory.\",\r\n" + 
			"\"capricorn\":\"Being a Capricornian, you tend to quite introvert and reserved, and at the same time hard and sturdy in terms your personality. You make your way to your destination with slow and steady pace. You have a strength of character and your ambition is something that you wont be willing to part at any cost. You are quite systematic and have a tremendous verve that you exhibit in everything you do. Your narcissistic approach towards life can get really unnerving for your mate and friends. You might also be restricted and misfortunes may come your way at various points, but this will make all the more strong and patient. You also tend to become orthodox and conventional at times, losing your broad-mindedness. While negotiating, you just cant believe someone until you have scrutinized the situation thoroughly, and you tend to lose out on a lot of effort and fun in this process. You are symbolic of authority and industriousness and these qualities are bound to increase your earnings greatly. But refrain from getting into the rat-race for money and power, lest you lose out on the more important things in life. Your unemotional and too professional approach and your reticence towards your colleagues at gatherings will not help your fortune. Qualities that make you lucky are common sense, steadfast, cooperative, and hardworking. Qualities that hinder your success are distrust, selfish, pessimistic, and conventional philosophy.\",\r\n" + 
			"\"aquarius\":\"Your zodiac sign is symbolized by the bearer of the jug of water, you provide the spiritual and intellectual health drink to the society and people around you. And the water has a tendency to flow ahead, so that you are forward looking, and optimistic, and think only in terms of growing inward and outward possession. You dispense knowledge, advice and even material possessions to those around you, who solicit your help and emotional support. You are the sign of brilliance and creativity, but these are not without the sufferings and sacrifices. You have distinctive, in fact a peculiar character – you are knowledgeable and intelligent yet hesitant to take important decisions or to give your opinion where it counts most. You are quite philanthropist, at least in thought, and have a great sense of humor. You are tolerant and don’t mind others poking fun at you. You are valuable to the world as you are a giver rather than a taker, you give hope and inspiration. Aquarians are amiable, charming and intuitive, but can get eccentric and unruly at times. They are also slow and shaky starters, taking their own time, but half way through, they might change their mind, irritating those with them. In your love life, you will have really good experiences, but some setback will keep piercing your heart at all times. Qualities that make you lucky are honesty, truthfulness, innovative, and insightful. Qualities that hinder your success are inconsistency, unusual, rebellious, and cautious.\",\r\n" + 
			"\"pisces\":\"Pisceans have a tendency to get pulled in two opposite directions. This inhibits their decision taking capability. They are quite open-minded towards their work and personal life, and demonstrate an immaculate insight and vision in material, technical and spiritual aspects. You have a particular artistic sophistication, which means that you have creativity with a polished edge. Your special characteristic is your love for beautiful things in life, and also a particular day-dreaming and inconsistent trait. You are too soft hearted and willingly help anyone in distress; this might also lead to problems to you because of your inability to say ‘no’. Even with the introspective and intuitive edge, you are moody and restless at times. Some depressions and dissatisfactions might lead you to pity yourself and escape from the distresses in life through drug addiction or drinking. You have a great inner strength, you only need to exhume it. You share your resources with others, even money, and think that others should do the same. The boundaries of possession are sometimes invisible or altogether meaningless to Pisceans, thus turning some into forgers and embezzlers. Qualities that make you lucky are caring, progressive, benevolent, and imaginative. Qualities that hinder your success are undecided, casual, not practical, and easygoing.\"\r\n" + 
			"}";
	public static void main(String[] a) throws JSONException {
		Calendar cal = new GregorianCalendar(1981, 7, 28);
		Zodiac zod = ZodiacUtil.getZodiac(cal.getTime());
		System.out.println(zod.getSunSign());
		System.out.println(zod.getCharactor());
	}
	
	public static Zodiac getZodiac(Date date) throws JSONException {
		
		if(date == null)
			return new Zodiac();
		
		String sign="";
		String element="";
		String chineseSign="";
		int month, day,year;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH) + 1;
		year = cal.get(Calendar.YEAR);

		if((month == 1) && (day <= 20) || (month == 12) && (day >= 22)) {
			sign = "Capricorn";
		} else if((month == 1) || (month == 2) && (day <= 19)) {
			sign = "Aquarius";
		} else if((month == 2) || (month == 3) && (day <= 20)) {
			sign = "Pisces";
		} else if((month == 3) || (month == 4) && (day <= 19)) {
			sign = "Aries";
		} else if((month == 4) || (month == 5) && (day <= 21)) {
			sign = "Taurus";
		} else if((month == 5) || (month == 6) && (day <= 21)) {
			sign = "Gemini";
		} else if((month == 6) || (month == 7) && (day <= 23)) {
			sign = "Cancer";
		} else if((month == 7) || (month == 8) && (day <= 23)) {
			sign = "Leo";
		} else if((month == 8) || (month == 9) && (day <= 23)) {
			sign = "Virgo";
		} else if((month == 9) || (month == 10) && (day <= 23)) {
			sign = "Libra";
		} else if((month == 10) || (month == 11) && (day <= 22)) {
			sign = "Scorpio";
		} else if(month == 12) {
			sign = "Sagittarius";
		}
		if((sign.equals("Aries"))||(sign.equals("Leo"))||(sign.equals("Sagittarius"))){
			element="Fire";
		}
		else if((sign.equals("Taurus"))||(sign.equals("Virgo"))||(sign.equals("Capricorn"))){
			element="Earth";
		}
		else if((sign.equals("Gemini"))||(sign.equals("Libra"))||(sign.equals("Aquarius"))){
			element="Air";
		}
		else if((sign.equals("Cancer"))||(sign.equals("Scorpio"))||(sign.equals("Pisces"))){
			element="Water";
		}

		int x = (1997 - year) % 12;
		if ((x == 1) || (x == -11)){
			chineseSign="Rat";
		}
		else{
			if (x == 0){
				chineseSign="Ox";
			}
			else{
				if ((x == 11) || (x == -1)){
					chineseSign="Tiger";
				}
				else{
					if ((x == 10) || (x == -2)){
						chineseSign="Rabbit";
					}
					else{
						if ((x == 9) || (x == -3)){
							chineseSign="Dragon";
						}
						else{
							if ((x == 8) || (x == -4)){ 
								chineseSign="Snake";
							}
							else{
								if ((x == 7) || (x == -5)){ 
									chineseSign="Horse";
								}
								else{
									if ((x == 6) || (x == -6)){ 
										chineseSign="Sheep";
									}
									else{
										if ((x == 5) || (x == -7)){ 
											chineseSign="Monkey";
										}
										else{
											if ((x == 4) || (x == -8)){
												chineseSign="Chicken";
											}
											else{
												if ((x == 3) || (x == -9)){
													chineseSign="Dog";
												}
												else{
													if ((x == 2) || (x == -10)){
														chineseSign="Pig";
													} 
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Zodiac zod = new Zodiac();
		zod.setSunSign(sign);
		zod.setElement(element);
		zod.setChineseSign(chineseSign);
		
		JSONUtils jUtils = new JSONUtils();
		jUtils.initialize(zodiac);
		String charactors = jUtils.getValue(sign.toLowerCase()).getStringValue();
		zod.setCharactor(charactors);

		return zod;
	}
}
