'''
Created on Apr 26, 2017
@author: Max Kelly
'''

if __name__ == '__main__':
    pass

import speech_recognition as sr

# obtain audio from the microphone
r = sr.Recognizer()
with sr.Microphone() as source:
    r.adjust_for_ambient_noise(source)
#         r.energy_threshold = 500
#         r.dynamic_energy_threshold = True
    r.non_speaking_duration = 0
        
        # print("Say something!")
    audio = r.listen(source)
    
    # # recognize speech using Sphinx
    # try:
    #     print("Sphinx thinks you said " + r.recognize_sphinx(audio))
    # except sr.UnknownValueError:
    #     print("Sphinx could not understand audio")
    # except sr.RequestError as e:
    #     print("Sphinx error; {0}".format(e))
        
        # recognize speech using Google Speech Recognition
    try:
            # for testing purposes, we're just using the default API key
            # to use another API key, use `r.recognize_google(audio, key="GOOGLE_SPEECH_RECOGNITION_API_KEY")`
            # instead of `r.recognize_google(audio)`
        print(r.recognize_google(audio))
    except sr.UnknownValueError:
        print("Google Speech Recognition could not understand audio")
    except sr.RequestError as e:
        print("Could not request results from Google Speech Recognition service; {0}".format(e))
    f = open('testfile.txt', 'w+')
    f.write(r.recognize_google(audio))
    f.close()