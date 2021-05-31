package peli;

import java.util.Scanner;

public class Peli {

	public static void main(String[] args) {

		Scanner lukija = new Scanner(System.in);

		int aika = 40;

		int pisteet = 0;

		int karma = 5;

		int syotto = 0;

		// Peliin vaikuttavat arvot. Pelikellossa jäljellä oleva aika, tehdyt pisteet ja
		// ns. karma, joka vaikuttaa lopuksi pelin lopputulokseen. Viimeinen arvo on
		// koriin johtaneet syötöt.

		int[] arvot = { aika, pisteet, karma, syotto };

		System.out.println("Peli alkaa!");

		// Pelin kulku on jaettu eri aliohjelmiin, joita pyöritetään niin kauan, kun
		// pelikellossa on aikaa.

		do {

			// Aliohjelmiin syötetään tallennettavat arvot, sekä lukija.

			arvot = ekaTapahtuma(arvot, lukija);

			arvot = tokaTapahtuma(arvot, lukija);

			arvot = kolmasTapahtuma(arvot, lukija);

			arvot = penkki(arvot);

			// Jos pelikellossa on yli 5 minuuttia, pelaaja ohjataan neljänteen tapahtumaan.

			if (arvot[0] > 5) {

				arvot = neljasTapahtuma(arvot, lukija);

				// Jos pelikellossa on alle 5 minuuttia, ohjelma suorittaa viimeisen tapahtuman,
				// joka voi ratkaista koko pelin.

			} else {
				arvot = vikaTapahtuma(arvot, lukija);
			}

		} while (arvot[0] > 0);

		// Pelin lopputulos.

		System.out.println();
		// Riippuen käyttäjän syötteistä peli päättyy voittoon tai häviöön.
		// 10 tai yli karmapistettä riittää voittoon, muulloin peli päättyy tappioon.
		if (arvot[2] >= 10)
			System.out.println("Voititte ottelun!");
		else
			System.out.println("Hävisitte ottelun.");

		// Pelaajan pisteet ja syötöt tulostetaan pelin lopuksi.

		System.out.println("Teit " + arvot[1] + " pistettä.");
		if (arvot[3] > 0)
		System.out.println("Lisäksi sait " + arvot[3] + " koriin johtanutta syöttöä.");

		lukija.close();

	}

	public static int[] ekaTapahtuma(int[] arvot, Scanner lukija) {

		System.out.println();
		System.out.println("Peliä on jäljellä: " + arvot[0] + " minuuttia");
		System.out.println();
		System.out.println(
				"Kuljetat palloa vastustajan puoliskolle ja näät, että kulmassa sijaitsevaa joukkuelaistasi puolustetaan löysästi. Mitä teet?\n\n1: Syötät hänelle pallon!\n2: Ajat itse korille.\n3: Yrität kolmen pisteen heittoa.");

		// Satunnaisuuteen käytettävä muuttuja.

		double rng = Math.random();

		// Käyttäjän syöte yhdestä kolmeen.

		int valinta = 0;

		valinta = lukija.nextInt();

		if (valinta >= 1 && valinta <= 3) {

			switch (valinta) {
			case 1:

				// Osaan tapahtumista vaikuttaa satunnainen muuttuja.
				// Eri valinnoilla voi saada eri määrän pisteitä, syöttöjä tai karmaa.

				if (rng >= 0.4) {
					System.out.println("Joukkuelaisesi heitti kolme pistettä. Hyvin pelattu!");
					// arvot[2] += 2; lisää kaksi pistettä karmaa. Kymmennellä voittaa pelin.
					arvot[2] += 2;
					break;
				} else {
					System.out.println("Syöttösi oli ennalta arvattavissa ja vastustaja saa varastettua pallon!");
					arvot[2]--;
					// +1 koriin johtanut syöttö.
					arvot[3]++;
					break;
				}
			case 2:
				if (rng >= 0.35) {
					System.out.println(
							"Sinut tuplataan, mutta ehdit syöttää pallon kaverillesi. Hän heittää kuitenkin ohi.");
					break;
				} else {
					System.out.println(
							"Ohitat puolustajasi ja donkkaat pallon koriin voimalla. Joukkueesi moraali on huipuillaan!");

					// +2 pistettä
					arvot[1] += 2;
					arvot[2]++;
					break;
				}
			case 3:
				if (rng >= 0.5) {
					System.out.println("Heität reilusti ohi. Huono valinta.");
					arvot[2]--;
					break;
				} else {
					System.out.println(
							"Osuit heiton reilusti kolmen pisteen arkin takaa! Mielenkiintoinen valinta, mutta teit 3 pistettä.");
					arvot[1] += 3;
					// Hyvä pelaaminen antaa sinulle karmaa, joka vaikuttaa pelin lopputulokseen
					// negatiivisesti tai positiivisesti.
					arvot[2]++;
					break;
				}
			}

		} else {
			System.out.println("Et keksinyt mitään ja menetit pallon.");
			arvot[2]--;
		}

		// Paljonko pelikellosta otetaan pois aikaa.
		arvot[0] -= 4;

		//Kaikki arvot palautetaan.
		return arvot;

	}

	public static int[] tokaTapahtuma(int[] arvot, Scanner lukija) {

		// Muutkin aliohjelmat toimivat samalla periaatteella kuin ensimmäinen.
		
		System.out.println();
		System.out.println("Peliä on jäljellä: " + arvot[0] + " minuuttia");
		System.out.println();
		System.out.println(
				"Sinä saat pallon vapaaheittoviivan tuntumassa. Näet, että sentteriäsi merkkaa huomattavasti lyhyempi puolustaja. Mitä teet?\n\n1: Syötät pallon toiselle joukkuelaiselle.\n2: Yrität kahden pisteen heittoa.\n3: Syötät pallon sentterille.");

		double rng = Math.random();

		int valinta = 0;

		valinta = lukija.nextInt();

		if (valinta >= 1 && valinta <= 3) {

			switch (valinta) {
			case 1:
				System.out.println("Tiimiläisesi ei odottanut syöttöä, joka johti pallonmenetykseen.");
				break;
			case 2:
				if (rng <= 0.5 && rng >= 0.1) {
					System.out.println("Sinua rikotaan heittäessäsi. Saat kaksi vapaaheittoa. Osut niistä molemmat.");
					arvot[1] += 2;
					arvot[2]++;
					break;
				} else if (rng <= 0.1) {
					System.out.println("Osuit toisen kahdesta vapaaheitosta.");
					arvot[1]++;
					break;
				} else {
					System.out.println("Heität niukasti ohi. Ei mikään paras päätös.");
					arvot[2]--;
					break;
				}
			case 3:
				if (rng >= 0.7) {
					System.out.println("Tiimiläisesi tekee helposti korin. Hyvä valinta.");
					arvot[2] += 2;
					arvot[3]++;
					break;
				} else {
					System.out.println("Syöttösi ei ole ihan kohdillaan ja joukkuelaisesi ei saa sitä kiinni.");
					arvot[2]--;
					break;
				}
			}

		} else {
			System.out.println("Et keksinyt mitään ja menetit pallon.");
			arvot[2]--;
		}

		// paljonko pelikellosta otetaan pois aikaa
		arvot[0] -= 4;

		return arvot;
	}

	public static int[] kolmasTapahtuma(int[] arvot, Scanner lukija) {

		System.out.println();
		System.out.println("Peliä on jäljellä: " + arvot[0] + " minuuttia");
		System.out.println();
		System.out.println(
				"Odotat syöttöä oikeassa kulmassa. Syöttö tulee sinua kohtia vihdoin, mutta 24:n sekuntin kellossa on vain muutama sekuntti jäljellä.\nMitä teet?\n\n1: Heität nopeasti.\n2: Syötät pallon lähimmälle joukkuelaisellesi.\n3: Yrität kalastaa rikettä.");

		double rng = Math.random();

		int valinta = 0;

		valinta = lukija.nextInt();

		if (valinta >= 1 && valinta <= 3) {

			switch (valinta) {
			case 1:
				if (rng >= 0.3) {
					System.out.println("Osut kolmen pisteen heiton viime hetkellä!");
					arvot[1] += 3;
					arvot[2]++;
					break;
				} else {
					System.out.println("Et ehdi saada kunnon otetta pallosta ja heität hieman ohi.");
					break;
				}
			case 2:
				System.out.println(
						"Kaverisi ei ehdi edes ottaa syöttöä vastaan. Olisi kannattanut itse yrittää heittoa.");
				arvot[2]--;
				break;
			case 3:
				if (rng >= 0.90) {
					System.out.println(
							"Saat puolustajan ilmaan ja hän rikkoo sinua. Saat kolme vapaaheittoa, mutta osut niistä vain yhden.");
					arvot[1]++;
					break;
				} else if (rng >= 0.80) {
					System.out.println(
							"Saat puolustajan ilmaan ja hän rikkoo sinua. Saat kolme vapaaheittoa ja niistä kaksi.");
					arvot[1] += 2;
					arvot[2]++;
					break;
				} else if (rng >= 0.4) {
					System.out.println(
							"Saat puolustajan ilmaan ja hän rikkoo sinua. Saat kolme vapaaheittoa ja osut ne kaikki!");
					arvot[1] += 3;
					arvot[2] += 2;
					break;
				} else {
					System.out.println("Et saa kalastettua rikettä ja aika loppuu.");
					arvot[2]--;
					break;
				}
			}

		} else {
			System.out.println("Et keksinyt mitään ja menetit pallon.");
			arvot[2]--;
		}

		// paljonko pelikellosta otetaan pois aikaa
		arvot[0] -= 4;

		return arvot;
	}

	public static int[] penkki(int[] arvot) {

		// Kolmannen tapahtuman jälkeen pelaaja lepää viisi minuuttia penkillä.
		
		System.out.println();
		System.out.println("Olet väsynyt, joten sinut otetaan viideksi minuutiksi penkille huilaamaan.");

		arvot[0] -= 5;

		return arvot;
	}

	public static int[] neljasTapahtuma(int[] arvot, Scanner lukija) {

		// Neljännessä tapahtumassa pelaaja palaa penkiltä kentälle. Neljäs tapahtuma suoritetaan vain kerran.
		
		System.out.println();
		System.out.println("Peliä on jäljellä: " + arvot[0] + " minuuttia");
		System.out.println();
		System.out.println(
				"Sinä palaat peliin levättyäsi vaihtopenkillä ja saat heti pallon sivurajan lähettyvillä. Tunnet olosi itsevarmaksi.\n\n1: Syötät pallon lähimmälle joukkuelaiselle.\n2: Heität kolmen pisteen heiton kaukaa.\n3: Ajat korille.");

		double rng = Math.random();

		int valinta = 0;

		valinta = lukija.nextInt();

		if (valinta >= 1 && valinta <= 3) {

			switch (valinta) {
			case 1:
				if (rng >= 0.5) {
					System.out.println("Joukkuelainen vastaanottaa syötön, mutta menettää pallon kuljetusyrityksessä.");
					break;
				} else {
					System.out.println("Syöttösi riistetään! Vastustava joukkue tekee kaksi pistettä läpiajosta.");
					arvot[2]--;
					break;
				}
			case 2:
				if (rng >= 0.5) {
					System.out.println("Osut kolmen pisteen heiton hyvin kaukaa. Hyvä heitto!");
					arvot[1] += 3;
					arvot[2]++;
					break;
				} else {
					System.out.println("Heität niukasti ohi. Parempi onni ensi kerralla.");
					arvot[2] += 2;
					break;
				}
			case 3:
				if (rng <= 0.5) {
					System.out.println(
							"Pääset ohi puolustajastasi helposti eikä korilla ole ketään. Teet kaksi pistettä.");
					arvot[1] += 2;
					arvot[2]++;
					break;
				} else {
					System.out.println(
							"Pääset ohi puolustajastasi helposti, mutta korilla tulee vastaan toinen puolustaja. Ehdit kuitenkin syöttää pallon joukkuelaiselle, kuka heittää ohi.");
					break;
				}
			}

		} else {
			System.out.println("Et keksinyt mitään ja menetit pallon.");
			arvot[2]--;
		}

		arvot[0] -= 4;

		return arvot;
	}

	public static int[] vikaTapahtuma(int[] arvot, Scanner lukija) {

		// Viimeinen tapahtuma suoritetaan myös vain kerran ja sen on kriittinen pelin lopputulokseen.
		
		double rng = Math.random();

		int valinta = 0;

		System.out.println();
		System.out.println("Peliä on jäljellä vain: " + arvot[0] + " minuuttia");
		System.out.println();

		// Mikäli olet pelannut hyvin tähän mennessä, voit keskittyä pisteiden
		// tekemiseen huoletta.
		if (arvot[2] > 12) {
			System.out.println(
					"Olet pelannut hyvin tähän asti ja joukkueellasi on valtava johto. Voittonne on periaatteessa jo taattu. Mitä teet pelin viimeisillä minuuteilla?\n\n1: Heität pitkän kolmosen.\n2: Et mitään, koska sillä ei ole väliä.\n3: Syötät pallon joukkuelaisellesi, kuka on pelannut hyvin.");

			valinta = lukija.nextInt();

			if (valinta >= 1 && valinta <= 3) {

				switch (valinta) {
				case 1:
					if (rng >= 0.5) {
						System.out.println("Osuit heiton erittäin kaukaa. Sait kolme pistettä lisää.");
						arvot[1] += 3;
						break;
					} else {
						System.out.println("Et osunut heittoa.");
						break;
					}
				case 2:
					System.out.println("Et tehnyt mitään.");
					break;
				case 3:
					if (rng > 0.25) {
						System.out.println("Kaverisi osui kolmen pisteen heiton! Hyvää joukkuepelaamista!");
						arvot[3]++;
						break;
					} else {
						System.out.println("Tiimiläisesi ei osunut heittoa. Yritys oli kuitenkin hyvä.");
						break;
					}
				}
			} else {
				System.out.println("Et keksinyt mitään ja menetit pallon.");
			}
			// Mikäli olet pelannut kohtuullisen hyvin, viimeinen tapahtuma vaikuttaa pelin
			// lopputulokseen

		} else if (arvot[2] >= 10 && arvot[2] <= 12) {
			System.out.println(
					"Olet pelannut kohtuullisen hyvin ja ottelu on ollut erittäin tiukka. Jokainen valinta on tässä vaiheessa kuitenkin erittäin tärkeä. Näet joukkuelaisen leikkaavan korille. Mitä teet pelin ratkaisevalla hetkellä?\n\n1: Yrität syöttöä leikkaavalle tiimiläiselle.\n2: Yrität kolmen pisteen heittoa.\n3: Syötät turvallisen syötön lähimmälle kaverille.");

			valinta = lukija.nextInt();

			if (valinta >= 1 && valinta <= 3) {

				switch (valinta) {
				case 1:
					if (rng >= 0.4) {
						System.out.println(
								"Syöttösi on millitarkka, joukkuelaisesi saa helpon korin. Loistavasti pelattu.");
						arvot[3]++;
						arvot[2] += 2;
						break;
					} else {
						System.out.println(
								"Syöttösi menee hieman ohi kohteesta! Vastustava joukkue riistää pallon ja tekee korin viime hetkellä!");
						arvot[2]--;
						break;
					}
				case 2:
					if (rng <= 0.4) {
						System.out.println("Osut kolmen pisteen heiton summerin soidessa! Uskomaton heitto!");
						arvot[1] += 3;
						arvot[2] += 3;
						break;
					} else {
						System.out.println(
								"Et osu heittoa! Vastustava joukkue heittää puolestaan kolmosen sisään viime sekunneilla!");
						arvot[2] -= 2;
						break;
					}
				case 3:
					if (rng > 0.25) {
						System.out.println("Joukkuelaisesi vastaanottaa syötön, mutta ei saa mitään aikaan.");
						break;
					} else {
						System.out.println(
								"Joukkuelaisesi saa syötön kiinni, mutta menettää pallon! Vastustajat ehtivät vielä tehdä korin!");
						arvot[2]--;
						break;
					}
				}
			} else {
				System.out.println("Et keksinyt mitään ja menetit pallon ratkaisevalla hetkellä!.");
				arvot[2] -= 2;
			}

			// Jos olet pelannut huonosti, sinulla on kuitenkin mahdollisuus voittaa vielä
			// ottamalla riskejä.
		} else {
			System.out.println(
					"Et ole pelannut niin hyvin tähän mennessä, mutta peliä ei ole vielä menetetty. Voittoon tarvitsette kuitenkin suuria riskejä.\nOlet pallottomana kulmassa. Mitä ajattelit tehdä?\n\n1: Yritä leikata korille.\n2: Koita saada avointa kolmen pisteen heittoa.\n3: Anna joukkuelaisen ottaa viimeinen heitto.");
			valinta = lukija.nextInt();

			if (valinta >= 1 && valinta <= 3) {

				switch (valinta) {
				case 1:
					if (rng >= 0.80) {
						System.out.println(
								"Leikkaat korille ja vastustajat reagoivat liian myöhään. Saat korin sekä vapaaheiton!");
						arvot[1] += 3;
						arvot[2] += 2;
						break;
					} else if (rng >= 0.40) {
						System.out.println("Leikkaat korille ja saat helposti kaksi pistettä!");
						arvot[1] += 2;
						arvot[2]++;
						break;
					} else {
						System.out
								.println("Leikkaat korille, mutta syöttö ei ole kohdillaan. Tiiminne menettää pallon.");
						break;
					}
				case 2:
					if (rng > 0.9) {
						System.out.println(
								"Et saa itseäsi täysin vapaaksi, mutta vastaanotat syötön ja yrität kolmen pisteen heittoa. Heitto menee jotenkin sisään ja sinua rikotaan heittäessä! Neljän pisteen pelaus!");
						arvot[1] += 4;
						arvot[2] += 3;
						break;
					} else if (rng > 0.5) {
						System.out.println(
								"Löydät suhteellisen avoimen heittopaikan. Heitto menee sisään! Kolme pistettä!");
						arvot[1] += 3;
						arvot[2] += 2;
						break;
					} else {
						System.out.println(
								"Et pääse vapaaksi ja joudut pakottamaan vaikean heiton ilmaan. Heitto menee pahasti ohi.");
						arvot[2]--;
						break;
					}
				case 3:
					if (rng > 0.80) {
						System.out.println(
								"Pallollinen tiimiläisesi tuplataan ja saat yllättäen syötön kulmaan. Heität pallon nopeasti kellon tikittäessä alas. Heitto menee sisään! Kolme pistettä viime hetkellä.");
						arvot[1] += 3;
						arvot[2] += 2;
						break;
					} else if (rng > 0.4) {
						System.out.println("Joukkuekaverisi heittää vaikean kolmosen. Heitto menee ohi.");
						break;
					} else {
						System.out.println(
								"Joukkuelainen heittää vaikean kolmosen sisään viime hetkellä! Kannatti luottaa tiimiin.");
						arvot[2] += 2;
						break;
					}
				}
			} else {
				System.out.println("Kukaan joukkuelaisesi ei keksinyt mitään!?");
			}
		}

		arvot[0] -= 2;
		return arvot;
	}

}
