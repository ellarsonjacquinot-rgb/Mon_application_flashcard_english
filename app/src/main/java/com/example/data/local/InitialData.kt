package com.example.data.local

import com.example.data.model.FlashcardEntity

object InitialData {
    fun getInitialCards(): List<FlashcardEntity> {
        return listOf(
            // --- LEVEL A2: ELEMENTARY / DIRECT SPOKEN ---
            FlashcardEntity(
                id = 1,
                niveau = "A2",
                theme = "Vie Quotidienne",
                phraseMalagasy = "Manao ahoana ny fahasalamana sy ny asa androany?",
                phraseAnglais = "How are things going with you today?",
                prononciation = "/haʊ ɑːr θɪŋz ˈɡoʊɪŋ wɪð juː təˈdeɪ/",
                vocabulaire = "things going = fandehan'ny raharaha; today = androany",
                grammaire = "Interrogative phrase used in daily spoken greeting. 'How are things going' is much more natural than 'How is your health'.",
                variantes = "How's everything going today? / How are you doing today?",
                exemple = "A: Hey Rova! How are things going with you today? B: Everything's great, thanks!",
                difficulte = "Facile"
            ),
            FlashcardEntity(
                id = 2,
                niveau = "A2",
                theme = "Vie Quotidienne",
                phraseMalagasy = "Mbola tsy fantatro tsara raha afaka ho avy aho.",
                phraseAnglais = "I'm still not really sure if I can make it.",
                prononciation = "/aɪm stɪl nɑːt ˈriːəli ʃʊr ɪf aɪ kæn meɪk ɪt/",
                vocabulaire = "make it = ho tonga / afaka avy; still = mbola",
                grammaire = "Use 'make it' in casual conversation to mean arriving or attending an event.",
                variantes = "I'm not quite sure if I can come yet. / I might not be able to come.",
                exemple = "A: Are you coming to the party tonight? B: I'm still not really sure if I can make it.",
                difficulte = "Moyen"
            ),
            FlashcardEntity(
                id = 3,
                niveau = "A2",
                theme = "Opinions & Émotions",
                phraseMalagasy = "Mba miangavy anao aho mba handefa izany SMS izany.",
                phraseAnglais = "Could you please send that text message for me?",
                prononciation = "/kʊd juː pliːz sɛnd ðæt tɛkst ˈmɛsɪdʒ fɔːr miː/",
                vocabulaire = "text message = SMS; send = mandefa",
                grammaire = "Polite request using modal verb 'Could you'. Softens imperative commands.",
                variantes = "Would you mind sending that text message? / Can you send that text for me?",
                exemple = "Could you please send that text message for me? My battery just died.",
                difficulte = "Facile"
            ),
            FlashcardEntity(
                id = 4,
                niveau = "A2",
                theme = "Voyages & Transports",
                phraseMalagasy = "Aiza no lalana mankany amin'ny fiantsonan meksipress?",
                phraseAnglais = "Which way is it to the local bus station?",
                prononciation = "/wɪtʃ weɪ ɪz ɪt tuː ðə ˈloʊkl bʌs ˈsteɪʃn/",
                vocabulaire = "which way = aiza ny lalana; bus station = fiantsonana",
                grammaire = "Direct question asking for directions. 'Which way' asks for orientation.",
                variantes = "How do I get to the bus station from here?",
                exemple = "Excuse me, which way is it to the local bus station?",
                difficulte = "Facile"
            ),
            FlashcardEntity(
                id = 5,
                niveau = "A2",
                theme = "Social & Conversations",
                phraseMalagasy = "Faly nahalala anao aho, ndao hihaona indray amin'ny manaraka.",
                phraseAnglais = "It was great meeting you, let's catch up again soon!",
                prononciation = "/ɪt wəz ɡreɪt ˈmiːtɪŋ juː, lɛts kætʃ ʌp əˈɡɛn suːn/",
                vocabulaire = "catch up = hihaona / hifampita vaovao; soon = amin'ny manaraka",
                grammaire = "'Catch up' is an informal phrasal verb meaning to meet and talk about recent events.",
                variantes = "Nice meeting you, let's talk again soon! / Great to see you!",
                exemple = "It was great meeting you, let's catch up again soon over coffee!",
                difficulte = "Facile"
            ),
            FlashcardEntity(
                id = 6,
                niveau = "A2",
                theme = "Urgences & Problèmes",
                phraseMalagasy = "Very ny finday-ko, afaka mampiasa ny anao ve aho ankehitriny?",
                phraseAnglais = "I lost my phone, can I borrow yours for a second?",
                prononciation = "/aɪ lɔːst maɪ foʊn, kæn aɪ ˈbɑːroʊ jʊrz fɔːr ə ˈsɛkənd/",
                vocabulaire = "borrow = mindrana / mampiasa fotoana fohy; phone = finday",
                grammaire = "Expressing past action ('lost') followed by present request ('can I borrow').",
                variantes = "I can't find my phone, could I use yours real quick?",
                exemple = "I lost my phone, can I borrow yours for a second to call my brother?",
                difficulte = "Facile"
            ),

            // --- LEVEL B1: INTERMEDIATE / NUANCED EXPRESSION ---
            FlashcardEntity(
                id = 7,
                niveau = "B1",
                theme = "Opinions & Émotions",
                phraseMalagasy = "Raha ny fahafantarako azy, tsara kokoa raha miandry ampitso izantsika.",
                phraseAnglais = "As far as I'm concerned, we'd be better off waiting until tomorrow.",
                prononciation = "/æz fɑːr æz aɪm kənˈsɜːrnd, wiːd biː ˈbɛtər ɔːf ˈweɪtɪŋ ənˈtɪl təˈmɑːroʊ/",
                vocabulaire = "as far as I'm concerned = raha ny amiko / raha ny fahitako azy; be better off = tsara kokoa",
                grammaire = "'Better off + V-ing' expresses a preference for a better situation or outcome.",
                variantes = "In my opinion, it's better if we wait until tomorrow. / Personally, I think we should wait.",
                exemple = "As far as I'm concerned, we'd be better off waiting until tomorrow when the rain stops.",
                difficulte = "Moyen"
            ),
            FlashcardEntity(
                id = 8,
                niveau = "B1",
                theme = "Travail & Affaires",
                phraseMalagasy = "Aza fohifohy fa lazao ahy tsara ny momba izany tetikasa izany.",
                phraseAnglais = "Don't hold back, fill me in on the details of that project.",
                prononciation = "/doʊnt hoʊld bæk, fɪl miː ɪn ɑːn ðə ˈdiːteɪlz əv ðæt ˈprɑːdʒɛkt/",
                vocabulaire = "fill me in = lazao ahy tsara / ampahafantaro ahy; hold back = mitsitsy teny",
                grammaire = "Phrasal verb 'fill someone in' means to provide necessary context or update someone.",
                variantes = "Give me all the details about the project. / Keep me updated on the project.",
                exemple = "Don't hold back, fill me in on the details of that project during our break.",
                difficulte = "Moyen"
            ),
            FlashcardEntity(
                id = 9,
                niveau = "B1",
                theme = "Récits & Expériences",
                phraseMalagasy = "Tsy nampoiziko mihitsy hoe izany no hitranga rehefa tonga tao aho.",
                phraseAnglais = "I never would have guessed that's what would happen when I arrived.",
                prononciation = "/aɪ ˈnɛvər wʊd hæv ɡɛst ðæts wʌt wʊd ˈhæpən wɛn aɪ əˈraɪvd/",
                vocabulaire = "would have guessed = ho nampoiziko; happen = hitranga",
                grammaire = "Modal perfect in the past ('would have guessed') reflecting on an unexpected past event.",
                variantes = "I had no idea that would happen when I got there.",
                exemple = "I never would have guessed that's what would happen when I arrived at the conference.",
                difficulte = "Difficile"
            ),
            FlashcardEntity(
                id = 10,
                niveau = "B1",
                theme = "Projets & Hypothèses",
                phraseMalagasy = "Raha manam-potoana kokoa aho, mba te hianatra teny vahiny hafa koa.",
                phraseAnglais = "If I had more free time, I'd definitely take up another language.",
                prononciation = "/ɪf aɪ hæd mɔːr friː taɪm, aɪd ˈdɛfɪnətli teɪk ʌp əˈnʌðər ˈlæŋɡwɪdʒ/",
                vocabulaire = "take up = hanomboka hianatra / hanao zava-baovao; definitely = tokoa",
                grammaire = "Second Conditional (If + Past Simple, Would + Verb) for unreal/hypothetical present situations.",
                variantes = "If I were less busy, I'd learn another foreign language.",
                exemple = "If I had more free time, I'd definitely take up Spanish or Japanese.",
                difficulte = "Moyen"
            ),
            FlashcardEntity(
                id = 11,
                niveau = "B1",
                theme = "Vie Quotidienne",
                phraseMalagasy = "Efa zatra ny hatsiaka eto an-toerana ve ianao ankehitriny?",
                phraseAnglais = "Have you gotten used to the cold weather around here yet?",
                prononciation = "/hæv juː ˈɡɑːtn juːst tuː ðə koʊld ˈwɛðər əˈraʊnd hɪr jɛt/",
                vocabulaire = "get used to = ho zatra; cold weather = hatsiaka",
                grammaire = "Structure 'Get used to + noun/V-ing' indicates the process of becoming accustomed to something.",
                variantes = "Are you accustomed to the local weather now? / Have you adjusted to the cold?",
                exemple = "You've been here two months! Have you gotten used to the cold weather around here yet?",
                difficulte = "Moyen"
            ),
            FlashcardEntity(
                id = 12,
                niveau = "B1",
                theme = "Social & Conversations",
                phraseMalagasy = "Tsy maninona raha tara kely ianao, fa ampahafantaro ahy fotsiny.",
                phraseAnglais = "It's no big deal if you're running late, just give me a heads-up.",
                prononciation = "/ɪts noʊ bɪɡ diːl ɪf jʊr ˈrʌnɪŋ leɪt, dʒʌst ɡɪv miː ə hɛdz ʌp/",
                vocabulaire = "heads-up = fampahafantarana mialoha; no big deal = tsy maninona mihitsy",
                grammaire = "'Give someone a heads-up' is an idiom for warning or notifying someone in advance.",
                variantes = "Don't worry if you're late, just let me know beforehand.",
                exemple = "It's no big deal if you're running late, just give me a heads-up when you leave.",
                difficulte = "Moyen"
            ),

            // --- LEVEL B2: UPPER INTERMEDIATE / IDIOMATIC & COMPLEX ---
            FlashcardEntity(
                id = 13,
                niveau = "B2",
                theme = "Opinions & Émotions",
                phraseMalagasy = "Toa tsy dia resy lahatra loatra momba io hevitra io aho amin'ny ankapobeny.",
                phraseAnglais = "To be honest, I'm somewhat skeptical about whether this approach will pan out.",
                prononciation = "/tuː biː ˈɑːnɪst, aɪm ˈsʌmwʌt ˈskɛptɪkl əˈbaʊt ˈwɛðər ðɪs əˈproʊtʃ wɪl pæn aʊt/",
                vocabulaire = "pan out = hanome vokatra tsara / hahomby; skeptical = misalasala / tsy resy lahatra",
                grammaire = "'Pan out' is an informal phrasal verb meaning to yield successful results or develop as planned.",
                variantes = "Frankly, I have my doubts about whether this idea will work.",
                exemple = "To be honest, I'm somewhat skeptical about whether this approach will pan out in the long run.",
                difficulte = "Difficile"
            ),
            FlashcardEntity(
                id = 14,
                niveau = "B2",
                theme = "Travail & Affaires",
                phraseMalagasy = "Tsy maintsy mamaha ity olana ity haingana izantsika mialoha ny fe-potoana.",
                phraseAnglais = "We really need to get to the bottom of this issue before the deadline looms.",
                prononciation = "/wiː ˈriːəli niːd tuː ɡɛt tuː ðə ˈbɑːtəm əv ðɪs ˈɪʃuː bɪˈfɔːr ðə ˈdɛdlaɪn luːmz/",
                vocabulaire = "get to the bottom of = hamantatra ny fototry ny olana; loom = efa akaiky dia akaiky",
                grammaire = "Idiom 'get to the bottom of' paired with active verb 'loom' for deadline pressure.",
                variantes = "We must solve the root cause of this problem before time runs out.",
                exemple = "We really need to get to the bottom of this issue before the deadline looms on Friday.",
                difficulte = "Difficile"
            ),
            FlashcardEntity(
                id = 15,
                niveau = "B2",
                theme = "Projets & Hypothèses",
                phraseMalagasy = "Raha nampitandrina ahy mialoha ianao, dia mety ho niala tamin'izany fandriky izany aho.",
                phraseAnglais = "Had you tipped me off in advance, I might have avoided falling into that trap.",
                prononciation = "/hæd juː tɪpt miː ɔːf ɪn ədˈvæns, aɪ maɪt hæv əˈvɔɪdɪd ˈfɑːlɪŋ ˈɪntuː ðæt træp/",
                vocabulaire = "tip off = mampitandrina / manome torohana fampahafantarana; in advance = mialoha",
                grammaire = "Inverted Third Conditional ('Had you + PP...' instead of 'If you had + PP...'), showing high-level formality.",
                variantes = "If you had warned me beforehand, I could have avoided that mistake.",
                exemple = "Had you tipped me off in advance, I might have avoided falling into that contract trap.",
                difficulte = "Difficile"
            ),
            FlashcardEntity(
                id = 16,
                niveau = "B2",
                theme = "Social & Conversations",
                phraseMalagasy = "Aza dia mandray an'izany am-poko loatra fa teo am-pahatezerana fotsiny izy.",
                phraseAnglais = "Take what he said with a grain of salt; he was just venting in the heat of the moment.",
                prononciation = "/teɪk wʌt hiː sɛd wɪð ə ɡreɪn əv sɔːlt; hiː wəz dʒʌst ˈvɛntɪŋ ɪn ðə hiːt əv ðə ˈmoʊmənt/",
                vocabulaire = "with a grain of salt = tsy raisina ho marina / tsy raisina am-poko; venting = mamoaka hatezerana",
                grammaire = "Two common English idioms: 'take with a grain of salt' and 'in the heat of the moment'.",
                variantes = "Don't take his words personally; he was just angry at that instant.",
                exemple = "Take what he said with a grain of salt; he was just venting in the heat of the moment.",
                difficulte = "Difficile"
            ),
            FlashcardEntity(
                id = 17,
                niveau = "A2",
                theme = "Vie Quotidienne",
                phraseMalagasy = "Fomba ahoana no ahafahako mandoa ny sarany amin'ny karatra?",
                phraseAnglais = "Is it possible for me to pay by credit card?",
                prononciation = "/ɪz ɪt ˈpɑːsəbl fɔːr miː tuː peɪ baɪ ˈkrɛdɪt kɑːrd/",
                vocabulaire = "pay by card = mandoa amin'ny karatra; possible = afaka / azo atao",
                grammaire = "Standard polite inquiry using 'Is it possible for me to...'.",
                variantes = "Do you accept credit cards here? / Can I pay with a card?",
                exemple = "Is it possible for me to pay by credit card, or is cash required?",
                difficulte = "Facile"
            ),
            FlashcardEntity(
                id = 18,
                niveau = "B1",
                theme = "Voyages & Transports",
                phraseMalagasy = "Misy fahatarana kely ny sidina noho ny andro ratsy.",
                phraseAnglais = "The flight has been slightly delayed on account of severe weather.",
                prononciation = "/ðə flaɪt hæz bɪn ˈslaɪtli dɪˈleɪd ɑːn əˈkaʊnt əv sɪˈvɪr ˈwɛðər/",
                vocabulaire = "on account of = noho ny; severe weather = andro ratsy dia ratsy",
                grammaire = "Passive voice with prepositions: 'on account of' replaces the simpler 'because of'.",
                variantes = "The flight is delayed due to bad weather conditions.",
                exemple = "Attention passengers: The flight has been slightly delayed on account of severe weather.",
                difficulte = "Moyen"
            ),
            FlashcardEntity(
                id = 19,
                niveau = "B2",
                theme = "Récits & Expériences",
                phraseMalagasy = "Rehefa nieritreritra indray aho, dia tajoko fa izany no fanapahan-kevitra tsara indrindra.",
                phraseAnglais = "Upon reflection, I came to realize that it was the blessing in disguise after all.",
                prononciation = "/əˈpɑːn rɪˈflɛkʃn, aɪ keɪm tuː ˈriːəlaɪz ðæt ɪt wəz ðə ˈblɛsɪŋ ɪn dɪsˈɡaɪz ˈæftər ɔːl/",
                vocabulaire = "blessing in disguise = zavatra ratsy no fahitana azy nefa nitondra soa; upon reflection = rehefa nieritreritra indray",
                grammaire = "Uses the classic idiom 'blessing in disguise' combined with formal opening 'Upon reflection'.",
                variantes = "Looking back, I realized it turned out to be a good thing after all.",
                exemple = "Losing that job was painful, but upon reflection, it was a blessing in disguise.",
                difficulte = "Difficile"
            ),
            FlashcardEntity(
                id = 20,
                niveau = "A2",
                theme = "Opinions & Émotions",
                phraseMalagasy = "Tiako be ity sakafo ity, tena matsiro tokoa!",
                phraseAnglais = "I really love this meal, it tastes absolutely delicious!",
                prononciation = "/aɪ ˈriːəli lʌv ðɪs miːl, ɪt teɪsts ˌæbsəˈluːtli dɪˈlɪʃəs/",
                vocabulaire = "delicious = matsiro; meal = sakafo",
                grammaire = "Adverb 'absolutely' intensifies non-gradable adjectives like 'delicious'.",
                variantes = "This food is amazing! / I really enjoy this dish!",
                exemple = "Thank you for cooking! I really love this meal, it tastes absolutely delicious!",
                difficulte = "Facile"
            )
        )
    }
}
