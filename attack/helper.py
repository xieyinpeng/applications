
import numpy as np

def perturb_image(xs, img):
    # If this function is passed just one perturbation vector,
    # pack it in a list to keep the computation the same
    if xs.ndim < 2:
        xs = np.array([xs])

    # Copy the image n == len(xs) times so that we can
    # create n new perturbed images
    tile = [len(xs)] + [1]*(xs.ndim+1)
    imgs = np.tile(img, tile)

    # Make sure to floor the members of xs as int types
    xs = xs.astype(int)
    for x,img in zip(xs, imgs):
        # Split x into an array of 5-tuples (perturbation pixels)
        # i.e., [[x,y,r,g,b], ...]
        pixels = np.split(x, len(x) // 3)
        for pixel in pixels:
            # At each pixel's x,y position, assign its rgb value
            x_pos, y_pos, *rgb = pixel
            img[x_pos, y_pos] = rgb

    return imgs

def attack_stats(df, models, network_stats):
    stats = []
    for model in models:
        val_accuracy = np.array(network_stats[network_stats.name == model.name].accuracy)[0]
        m_result = df[df.model == model.name]
        pixels = list(set(m_result.pixels))

        for pixel in pixels:
            p_result = m_result[m_result.pixels == pixel]
            success_rate = len(p_result[p_result.success]) / len(p_result)
            stats.append([model.name, val_accuracy, pixel, success_rate])

    return pd.DataFrame(stats, columns=['model', 'accuracy', 'pixels', 'attack_success_rate'])
