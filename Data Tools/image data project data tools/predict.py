import cv2
import numpy as np
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image as keras_image
from tensorflow.keras.applications.inception_v3 import preprocess_input,decode_predictions
from PIL import Image, ImageTk
import tkinter as tk
import matplotlib.pyplot as plt
import requests
import regex as re
from bs4 import BeautifulSoup

# Load the model (replace 'your_model.h5' with your actual model file)
model = load_model('final1.h5')

# Bird labels corresponding to the classes in your model
bird_labels = [
    'ABBOTTS BABBLER', 'ABBOTTS BOOBY', 'ABYSSINIAN GROUND HORNBILL', 'AFRICAN CROWNED CRANE',
    'AFRICAN EMERALD CUCKOO', 'AFRICAN FIREFINCH', 'AFRICAN OYSTER CATCHER', 'AFRICAN PIED HORNBILL',
    'AFRICAN PYGMY GOOSE', 'ALBATROSS', 'ALBERTS TOWHEE', 'ALEXANDRINE PARAKEET', 'ALPINE CHOUGH',
    'ALTAMIRA YELLOWTHROAT', 'AMERICAN AVOCET', 'AMERICAN BITTERN', 'AMERICAN COOT', 'AMERICAN DIPPER',
    'AMERICAN FLAMINGO', 'AMERICAN GOLDFINCH'
]

# Create a tkinter window
root = tk.Tk()
root.title("Bird Classification")

# Create a label for displaying the video feed
label = tk.Label(root)
label.pack()

# Open a connection to the camera (usually 0 for the default built-in camera)
cap = cv2.VideoCapture(0)

def update_video_feed():
    ret, frame = cap.read()

    # Resize and preprocess the image
    img_array = cv2.resize(frame, (224, 224))
    img_array = np.expand_dims(img_array, axis=0)
    img_array = preprocess_input(img_array)

    # Make predictions using the loaded model
    predictions = model.predict(img_array)

    # Assuming a classification model with categorical output
    class_index = np.argmax(predictions)
    predicted_class = bird_labels[class_index]

    # Display the predicted class on the frame
    cv2.putText(frame, f"Predicted: {predicted_class}", (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2)

    # Convert the BGR frame to RGB format for displaying in tkinter
    rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    img = Image.fromarray(rgb_frame)
    img = ImageTk.PhotoImage(image=img)

    # Update the label with the new image
    label.img = img
    label.config(image=img)

    # Schedule the update after a delay (in milliseconds)
    root.after(10, update_video_feed)

# Schedule the initial update
update_video_feed()

# Run the tkinter event loop
root.mainloop()

# Release the camera
cap.release()

# Load the image (replace 'path/to/your/image.jpg' with the actual path)
image_path = "test1.jpg"
img = Image.open(image_path)

# Preprocess the image
img = img.resize((224, 224))  # Adjust size if needed
img_array = keras_image.img_to_array(img)
img_array = np.expand_dims(img_array, axis=0)
img_array = preprocess_input(img_array)

# Make predictions using the loaded model
predictions = model.predict(img_array)

# Display the image
plt.imshow(img)
plt.show()

# Assuming a classification model with categorical output
class_index = np.argmax(predictions)
predicted_class = bird_labels[class_index]

print(f"Predicted class index: {class_index}")
print(f"Predicted class label: {predicted_class}")

# Web scraping from ebird.org to get a recording of the bird and some information about it
try:
    search = predicted_class
    search = search.replace(' ','+')
    google = f'https://www.google.com/search?q={search}+ebird'

    # Send a GET request to the URL
    response = requests.get(google)

    # Check if the request was successful (status code 200)
    if response.status_code == 200:
        # Parse the HTML content of the page
        soup = BeautifulSoup(response.text, 'html.parser')
        a = soup.findAll('a',href=True)

    for x in a:
        if('ebird.org' in x['href']):
            link = re.findall('https[^&]+',x['href'])[0]
            break

    response = requests.get(link)
    if response.status_code==200:
        soup = BeautifulSoup(response.text,'html.parser')
        print(soup.find('p',class_='u-stack-sm').text)
        bird_code = link[link.rfind('/')+1:]
        audio_link = f'https://search.macaulaylibrary.org/catalog?taxonCode={bird_code}&mediaType=audio'

    from selenium import webdriver
    from selenium.webdriver.common.by import By
    from selenium.webdriver.support.ui import WebDriverWait
    from selenium.webdriver.support import expected_conditions as EC
    from contextlib import redirect_stdout
    import io

    # Set up the Selenium WebDriver (you need to have the appropriate driver installed)

    driver = webdriver.Chrome()

    # Navigate to the URL
    driver.get(audio_link)

    # Set a maximum wait time for elements to be loaded (adjust as needed)
    wait = WebDriverWait(driver, 10)
    element= wait.until(EC.presence_of_element_located((By.CLASS_NAME, 'ResultsGallery-link')))
    audio_link = element.get_attribute('href')
    audio_code = audio_link.find('asset/')+len('asset/')
    audio_code = audio_link[audio_code:]
    audio_link = f'https://cdn.download.ams.birds.cornell.edu/api/v1/asset/{audio_code}/audio'
    driver.quit()


    response = requests.get(audio_link)
    if response.status_code ==200:
        with open('f.mp3','wb') as file:
            file.write(response.content)
    import pygame
    import time
    pygame.mixer.init()
    pygame.mixer.music.load('f.mp3')
    pygame.mixer.music.play()

    while pygame.mixer.music.get_busy():
        time.sleep(1)
    pygame.mixer.quit()
except:
    print('Something wrong happened')

    