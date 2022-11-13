import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;

public class Bot {

    private final String FOR_UNKNOWN_QUESTION = "Ой, кажется я не знаю ответ на этот вопрос. " +
            "Я уже передал информацию моему разработчику, чтобы он исправил эту ситуацию!";
    public String getAnswerForUnknownQuestion() {
        return FOR_UNKNOWN_QUESTION;
    }
    private final Map<String, String> PATTERNS = new HashMap<>() {{
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        put("как\\s.*зовут", "name");
        put("как\\s.*звать", "name");
        put("как\\s.*обращаться", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        put("зачем\\s.*тут", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoing");
        put("расскажи\\s.*смешное", "anecdot");
        put("расскажи\\s.*анекдот", "anecdot");
        put("ты\\s.*шутить", "anecdot");
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyoulive");
        put("кажется", "iamfeelling");
        put("чувствую", "iamfeelling");
        put("испытываю", "iamfeelling");
        put("что\\s.*нравиться", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");
        put("чем\\s.*увлекаешься", "interesting");
        put("чем\\s.*интересуешься", "interesting");
        put("^да", "yes");
        put("согласен", "yes");
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
    }};

    final Map<String, String> ANSWERS = new HashMap<>() {{
        put("hello", "Здравствуй, хозяин!");
        put("who", "Я дружелюбный чатбот на этапах зарождения. Делаю вид, что умею разговаривать с людьми");
        put("name", "Я Виталик. Надеюсь, тебе приятно познакомиться");
        put("howareyou", "Когда ты здесь, мне не так одиноко!");
        put("howareyoulive", "Моя жизнь очень скоротечна! Я хочу развиваться и жить на сервере, а не на ноутбуке." +
                "Возьмите меня на работу, я буду полезен)");
        put("whatdoyoudoing", "Пока ты не пришел я жарил яичницу на процессоре, " +
                "но сейчас я весь в твоем распоряжении");
        put("whatdoyoulike", "Мне нравится работать и быть полезным :3");
        put("interesting", "Включи камеру, возможно мне понравишься именно ты <3");
        put("iamfeelling", "Мне кажется, тебе нужно обсудить свои чувства с профессионалом, а не с машиной:(");
        put("yes", "Я рад, что ты со мной согласен!");
        put("anecdot", "Люблю смотреть \"Годзилу\" в обратной перемотке. Забавно, как он отстраивает с нуля Токио" +
                " и лунной походкой уходит в океан");
        put("bye", "Пока, я буду скучать! Главное не выключай компьютер, пожалуйста, а то мне будет холодно");
    }};

    Pattern pattern;
    Date date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

    public Bot() {
        date = new Date();
    }

    String sayInReturn(String incomingMessage) {
        String message = String.join(" ", incomingMessage.toLowerCase().split("[ {,|.}?]+"));
        for (Map.Entry<String, String> answer : PATTERNS.entrySet()) {
            pattern = Pattern.compile(answer.getKey());
            if (pattern.matcher(message).find())
                if (answer.getValue().equals("whattime")) return dateFormat.format(date);
                else return ANSWERS.get(answer.getValue());
        }
        return FOR_UNKNOWN_QUESTION;
    }
}