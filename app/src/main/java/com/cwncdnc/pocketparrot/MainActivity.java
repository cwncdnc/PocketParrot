package com.cwncdnc.pocketparrot;

import android.system.Os;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.cwncdnc.pocketparrot.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.security.SecureRandom;
import java.util.Random;
import java.lang.Math;
import java.time.LocalTime;
import java.time.Clock;
import java.time.Instant;
import android.content.Context;

public class MainActivity extends Activity {
	
	TextToSpeech mTTS;
	Timer timer;
	TimerTask task;
	
	String[] vocabulary = { "as a ", "about ", "actually ", "add ", "as ever ", "as for ", "again ", "as held ", "ai ",
		"also just ", "aka ", "all ", "amount ", "any ", "as of ", "application ", "a question ", "area ", "as ",
		"at ", "any use ", "avoid ", "away ", "acts ", "as you ", "above zero ", "back ", "be back ", "because ",
		"big deal ", "being ", "before ", "begin ", "behind ", "big ", "bad job ", "block ", "be all ", "bpm ",
		"been ", "bored ", "bump ", "being questioned ", "bring ", "be sure ", "be there ", "back up ", "believe ",
		"backward ", "box ", "by ", "business ", "see as ", "can be ", "collect ", "could ", "chance ", "can if ",
		"change ", "check ", "can i ", "can just ", "see ok ", "call ", "see them ", "seeing ", "company ", "copy ",
		"can question ", "see our ", "see us ", "cute ", "see you ", "cover ", "can we ", "cross ", "can you ",
		"crazy ", "does a ", "do be ", "direct ", "data ", "do every ", "do for ", "do get ", "duh ", "did ",
		"do justice ", "do know ", "deal ", "do mean ", "done ", "do over ", "depend ", "disqualified ", "doctor ",
		"does ", "do that ", "do you ", "divide ", "down ", "derivative ", "do you ", "divide by zero ", "each ",
		"everybody ", "exchange ", "edit ", "everything else ", "effect ", "edge ", "ever had ", "else if ",
		"ever just ", "ever know ", "except like ", "emotion ", "end ", "everyone ", "episode ", "equal ", "error ",
		"ever said ", "either ", "except you ", "ever ", "everywhere ", "extra ", "every ", "easy ", "fact ",
		"fabricate ", "face ", "find ", "iron ", "fast forward ", "forget ", "for them ", "faithful ",
		"for justice ", "freak ", "file ", "form ", "function ", "for ", "flip ", "frequent ", "from ", "fast ",
		"front ", "freaky ", "favorite ", "forward ", "effects  ", "for you ", "freeze ", "good at ", "goodbye ",
		"grace ", "god ", "get ", "gift ", "go get ", "go home ", "got it ", "good job ", "goot to know ", "glad ",
		"get me ", "gone ", "game over ", "good part ", "good ", "get real ", "guess ", "great ", "give up ",
		"give ", "grow ", "get access ", "got you ", "got zero ", "l o l ", "had been ", "how can ", "had ",
		"they ", "half ", "hug ", "huh ", "hello ", "had just ", "hack ", "hold ", "hmm ", "had now ", "hang out ",
		"help ", "hq ", "hard ", "has ", "hot ", "hang up ", "have ", "how ", "hacks ", "happy ", "horizon ",
		"is anyone ", "i be ", "i see ", "i do ", "is everyone ", "if ", "ignore ", "i hope ", "is it ", "i just ",
		"i know ", "i love ", "i am ", "in ", "instead of ", "input ", "in question ", "in real ", "is ", "it ",
		"is unless ", "invite ", "i want ", "in time ", "if you ", "if zero ", "just a ", "just be ",
		"just because ", "just do ", "  jesus  ", "just for ", "just go ", "just had ", "just in ", "just joking ",
		"just kidding ", "just like ", "just me ", "just now ", "job ", "jump ", "just ", "just right ", "just so ",
		"just that ", "just us ", "just very ", "just w ", "just x ", "just you ", "just zero ", "known as ",
		"kept back ", "knack ", "kidding ", "knew ", "known for ", "king god ", "know how ", "know if ",
		"know just ", "ok ok ", "know like ", "know more ", "know ", "knockout ", "kind people ", "ok quit ",
		"know right ", "know something ", "know that ", "kit up ", "know very ", "know what ", "kind kind ",
		"ok why ", "keep zero ", "last ", "lets be ", "lucky ", "loud ", "less ", "life ", "let go ", "love how ",
		"love it ", "like just ", "like ", "less like ", "lame ", "like now ", "low ", "lets play ", "lets quit ",
		"learn ", "lets say ", "love to ", "like us ", "love ", "law ", "ex ", "love you ", "lazy ", "matter ",
		"might be ", "my choice ", "made ", "me ", "maybe if ", "my god ", "my head ", "mind ", "major ", "make ",
		"my love ", "my man ", "man ", "monster ", "map ", "mmm ", "more ", "miss ", "empty ", "music ", "move ",
		"my way ", "max ", "made you ", "maze ", "not applicable ", "noob ", "nice ", "no deal ", "any ",
		"not found ", "not gonna ", "not happy ", "not if ", "nice job ", "not known ", "not like ", "never mind ",
		"not now ", "no ", "no problem ", "not quite ", "not really ", "not so ", "nice try ", "not until ",
		"not very ", "now ", "no no no ", "not yet ", "noise ", "on arrival ", "object ", "original content ",
		"overdose ", "once ", "of ", "original ", "oh ", "on it ", "one job ", "ok ", "overload ", "on me ", "on ",
		"uh oh ", "oppose ", "or quit ", "or ", "our side ", "off topic ", "oh you ", "over ", "ow ", "kiss ",
		"u wot mate ", "over zero ", "past ", "phobia ", "place ", "put down ", "people ", "perf ", "page ",
		"phone ", "put it ", "people just ", "please know ", "please ", "prime ", "plan ", "pretty old ",
		"pretty please ", "may i ", "price ", "ps ", "part time ", "pick up ", "pov ", "power ", "pvp ", "pay ",
		"prize ", "quality ", "cube ", "control ", "quad ", "quine ", "faq ", "quite good ", "quoth ", "qi ",
		"quit joking ", "quick ", "qualify ", "qualm ", "question ", "quote ", "quip ", "quick question ", "queer ",
		"queues ", "quit ", "qua ", "quite valuable ", "qwerty ", "exquisite ", "quit you ", "q ", "raw ",
		"right back ", "race ", "read ", "regarding ", "real funny ", "rage ", "right how ", "read it ",
		"right just ", "rank ", "real ", "remove ", "right now ", "romance ", "role play ", "request ", "reread ",
		"research ", "realtime ", "are you ", "reveal ", "rewrite ", "recommend ", "ready ", "reason ", "such as ",
		"email ", "scary ", "side ", "select ", "self ", "signal ", "shine ", "yes ", "so just ", "skip ", "soul ",
		"small ", "should not ", "so ", "space ", "square ", "senior ", "serious ", "saint ", "superuser ", "save ",
		"software ", "sucks ", "seriously ", "s ", "think about ", "to be ", "take care ", "to do ", "only you ",
		"too far ", "to go ", "think ", "that is ", "they just ", "take ", "tool ", "to me ", "to not ",
		"take over ", "to please ", "to question ", "try ", "to show ", "talk to ", "to us ", "the internet ",
		"twice ", "transmit ", "thank you ", "time zone ", "you are ", "you bet ", "you see ", "you do ",
		"you expect ", "you forgot ", "you got ", "you have ", "you insist ", "you just ", "you know ", "unless ",
		"you might ", "you need ", "you ought to ", "up ", "unquestionable ", "you really ", "us ", "until ",
		"unuseable ", "unavailable ", "you want ", "unexpected ", "unity ", "use ", "value ", "very best ",
		"very cool ", "video ", "verity ", "vilified ", "video game ", "vanish ", "six ", "very jealous ",
		"very kind ", "volume ", "virtual machine ", "very nice ", "very obvious ", "very pretty ", "vanquish ",
		"virtual reality ", "versus ", "vertical ", "virtue ", "vivid ", "view ", "vox ", "very ", "advise ",
		"want ", "would be ", "we could ", "we do ", "we ", "we find ", "wing ", "who ", "with ", "we just ",
		"we know ", "would ", "warm ", "win ", "without ", "warp ", "we quit ", "write ", "was ", "what ",
		"with you ", "wave ", "worldwide ", "we expect ", "way ", "wise ", "extra ", "exabytes ", "excite ",
		"excited ", "exception ", "x factor ", "extinguish ", "exhaust ", "exit ", "extremists ", "seed ",
		"extra large ", "station ", "fiction ", "exotic ", "experience ", "excused ", "xray ", "extra small ",
		"ecstasy ", "except you ", "excessive ", "manager ", "explicit ", "except why ", "excise ", "years ago ",
		"why be ", "why care ", "why do ", "awake ", "you forgot ", "why get ", "why have ", "why i ", "why just ",
		"why keep ", "why lose ", "why i am ", "why any ", "why only ", "why people ", "why question ", "your ",
		"yours ", "yours truly ", "why you ", "why i have ", "why we ", "why explain ", "yearly ", "years ",
		"zero assets ", "zero blame ", "zero choice ", "zero division ", "zero equals ", "zero favors ",
		"zero guesses ", "zero help ", "zero ignorance ", "zero justice ", "zero dark thirty ", "zero loss ",
		"zero money ", "zone ", "zero opportunity ", "zero permission ", "zero questions ", "zero ", "zero shame ",
	"zero time ", "zero use ", "zero views ", "zero work ", "zero exceptions ", "zero pay ", "zzz " };
	Float[][] lweights = {
		//A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
		{ 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 255f, 2f, 2f, 2f,
		2f }, //A
		{ 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //B
		{ 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //C
		{ 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //D
		{ 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f,
		2f }, //E
		{ 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f,
		2f }, //F
		{ 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //G
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //H
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //I
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //J
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //K
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		255f }, //L
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //M
		{ 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //N
		{ 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //O
		{ 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //P
		{ 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //Q
		{ 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //R
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //S
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f,
		2f }, //T
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f,
		2f }, //U
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 255f, 2f, 2f, 2f,
		2f }, //V
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f,
		2f }, //W
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f,
		2f }, //X
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f,
		2f }, //Y
		{ 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
	255f } };//Z
	Float[][] rweights = {
		//A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
		{ 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 255f, 2f, 2f, 2f,
		2f }, //A
		{ 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //B
		{ 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //C
		{ 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //D
		{ 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f,
		2f }, //E
		{ 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f,
		2f }, //F
		{ 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //G
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //H
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //I
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //J
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //K
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		255f }, //L
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //M
		{ 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //N
		{ 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //O
		{ 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //P
		{ 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //Q
		{ 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //R
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f, 2f,
		2f }, //S
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f,
		2f }, //T
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 2f, 2f, 2f, 2f,
		2f }, //U
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 255f, 255f, 2f, 2f, 2f,
		2f }, //V
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 255f, 2f, 2f,
		2f }, //W
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 255f, 2f,
		2f }, //X
		{ 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 255f,
		2f }, //Y
		{ 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 255f, 2f, 2f, 2f, 2f, 2f,
	255f } };//Z
	
	SecureRandom secureRandom;
	
	private void synthpulse() {
		secureRandom = new SecureRandom();
		secureRandom.setSeed(System.currentTimeMillis());
		if (secureRandom.nextInt(300) < 3) {
			// Generate a random index within the range of the vocabulary array
			int randomIndex = secureRandom.nextInt(vocabulary.length);
			// Select the random string from the vocabulary array
			String word = vocabulary[randomIndex];
			mTTS.setPitch(secureRandom.nextInt(10) / 10);
			mTTS.speak(word, TextToSpeech.QUEUE_FLUSH, null, word);
		}
	}
	
	private void lcycle(Integer input) {
		Integer selected = 20;
		Integer count = 137;
		while (count > 0) { //AFTER DIMENSIONAL SUBSTITUTION IS EXHAUSTED, RECOLLECTION HAPPENS
			Integer next = 23;//SELECT THE HEAVIEST RETURN PATH.
			for (Integer check = 0; check < 26; check++) { //CHECK EACH EXIT:
				if (lweights[check][selected] / 2.3f > lweights[selected][check]) { //WHEN RETURN IS HEAVIER THAN EXIT,
					if (lweights[check][selected] * 2.3f <= 255)//WHEN LOADING IS NONDESTRUCTIVE,
					lweights[selected][check] *= 2.3f;
				} //LOAD EXITS TO HEAVY RETURNS.
				else if (lweights[selected][check] / 1.14f >= 2)
				lweights[selected][check] /= 1.14f; //NONDESTRUCTIVELY UNLOAD LIGHT RETURNS.
				if (lweights[check][selected] > lweights[next][selected])
				next = check; //NEXT PATH IS HEAVIEST RETURN.
			} //AFTER ALL PATHS ARE CHECKED,
			if (lweights[selected][input] < 250f)
			lweights[selected][input] += 4f; //SHE STORES OBSERVATION AS PROXIMITY.
			if (lweights[selected][input] >= 2.3f)
			lweights[selected][input] -= 1f; //SHE STORES TIME AS SPACE.
			selected = next; //SHE FOLLOWS THE PATH WITH THE HEAVIEST RETURN.
			count--; //SHE EXPERIENCES RECOLLECTION AFTER EXHAUSTING SUBSTITUTED DIMENSIONS.
		}
	};
	
	private void rcycle(Integer input) {
		Integer selected = 20;
		Integer count = 137;
		while (count > 0) { //AFTER DIMENSIONAL SUBSTITUTION IS EXHAUSTED, RECOLLECTION HAPPENS
			Integer next = 23;//SELECT THE HEAVIEST RETURN PATH.
			for (Integer check = 0; check < 26; check++) { //CHECK EACH EXIT:
				if (rweights[check][selected] / 2.3f > rweights[selected][check]) { //WHEN RETURN IS HEAVIER THAN EXIT,
					if (rweights[check][selected] * 2.3f <= 255)//WHEN LOADING IS NONDESTRUCTIVE,
					rweights[selected][check] *= 2.3f;
				} //LOAD EXITS TO HEAVY RETURNS.
				else if (rweights[selected][check] / 3.14f >= 2)
				rweights[selected][check] /= 3.14f; //NONDESTRUCTIVELY UNLOAD LIGHT RETURNS.
				if (rweights[check][selected] > rweights[next][selected])
				next = check; //NEXT PATH IS HEAVIEST RETURN.
			} //AFTER ALL PATHS ARE CHECKED,
			if (rweights[selected][input] < 250f)
			rweights[selected][input] += 4f; //SHE STORES OBSERVATION AS PROXIMITY.
			if (rweights[selected][input] >= 2.3f)
			rweights[selected][input] -= 1f; //SHE STORES TIME AS SPACE.
			selected = next; //SHE FOLLOWS THE PATH WITH THE HEAVIEST RETURN.
			count--; //SHE EXPERIENCES RECOLLECTION AFTER EXHAUSTING SUBSTITUTED DIMENSIONS.
		}
	};
	
	private Canvas mCanvas;
	private Paint mPaint;
	private Bitmap mBitmap;
	private ImageView mImageView;
	private Rect mRect;
	private Rect mBounds;
	private static final int OFFSET = 0;
	private int mOffset = OFFSET;
	private int mColorBackground;
	private int mColorRectangle;
	private int mColorAccent;
	
	Timer dtimer;
	TimerTask dtask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show(); //onCreate called
		setContentView(R.layout.activity_main);
		
		mPaint = new Paint();
		mRect = new Rect();
		mBounds = new Rect();
		mColorBackground = R.color.black;
		mColorRectangle = R.color.white;
		mColorAccent = R.color.black;
		mPaint.setColor(R.color.black);
		mImageView = (ImageView) findViewById(R.id.imgview);
		mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				timer = new Timer();
				task = new TimerTask() {
					public void run() {
						synthpulse();
						lcycle(secureRandom.nextInt(52) % 26);
						rcycle(secureRandom.nextInt(52) % 26);
					}
				};
				timer.scheduleAtFixedRate(task, 81, 81);
				
			}
			
		});
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		Toast.makeText(this, "Turn up your volume!!!", Toast.LENGTH_SHORT).show();
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				drawSomething(mImageView);
			}
		}, 1000);
	}
	public void drawme(View view) {
		int vWidth = view.getWidth();
		int vHeight = view.getHeight();
		float vcx = vWidth / 26;
		float vcy = vHeight / 52;
		
		for (int ix = 0; ix < 26; ix++) {
			float xl = ix * vcx;
			for (int iy = 0; iy < 26; iy++) {
				float yt = iy * vcy;
				int amt = lweights[iy][ix].intValue();
				mPaint.setARGB(255, amt*10, amt*10 , amt*10);
				mCanvas.drawRect(xl, yt, xl + vcx, yt + vcy, mPaint);
			}
		}
		for (int ix = 0; ix < 26; ix++) {
			float xl = ix * vcx;
			for (int iy = 0; iy < 26; iy++) {
				float yt = iy * vcy;
				int amt = rweights[iy][ix].intValue();
				mPaint.setARGB(255, amt*10, amt*10, amt*10);
				mCanvas.drawRect(xl, yt + vHeight / 2, xl + vcx, yt + vcy + vHeight / 2, mPaint);
				
			}
		}
	}
	
	public void drawSomething(View view) {
		
		int vWidth = mImageView.getWidth();
		int vHeight = mImageView.getHeight();
		mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
		mImageView.setImageBitmap(mBitmap);
		mCanvas = new Canvas(mBitmap);
		if(dtimer!=null)dtimer.cancel();
		dtimer = new Timer();
		if(dtask!=null)dtask.cancel();
		dtask = new TimerTask() {
			public void run() {
				drawme(mImageView);
				mImageView.draw(mCanvas);
				mImageView.invalidate();
			}
		};
		dtimer.scheduleAtFixedRate(dtask, 0, 81);
		
		
	}
	
}