package zxy.commons;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ImageDelegate {
    public static final String[] IMAGES = new String[] {
            "http://www.freeimageslive.com/galleries/nature/abstract/pics/abstract01863.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/arboretum_tree_rings.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/autumn_background.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/barbedwire00061.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/brownleaves02899.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/brown_bracken.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/bush01481.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/bush02758.jpg",
            "http://www.freeimageslive.com/galleries/nature/abstract/preview/coastal02781.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/horse.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/birds520.JPG",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/birds_on_wire.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/black_and_white_lemur.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/butterfly.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/cat02151.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/dog.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/dog0071.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/domestic_goose.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/pics/domestic_rabbit.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/pics/donkey.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/pics/dragonfly.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/dragonfly_pond.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/duck3267.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/duck04090065.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/emu.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/flying_hawk.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/gekko.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/giraffe.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/goat0191.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/goatP0189.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/goldfish4925.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/goldfish4927.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/guinea_pig.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/hawk.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/hens4947.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/highlandcows3795.jpg",
            "http://www.freeimageslive.com/galleries/nature/animals/preview/horses.jpg",
    };

    public static final List<String> IMAGE_LIST = Arrays.asList(IMAGES);

    public static int imageCount() {
        return IMAGES.length;
    }

    public static List<String> getImageList() {
        return IMAGE_LIST;
    }

    public static String randomImgage() {
        Random random = new Random();
        return IMAGES[random.nextInt(IMAGES.length)];
    }
}
