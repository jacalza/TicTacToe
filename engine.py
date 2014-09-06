import urllib2
def engine(input_data):
        play = -1
        try:
                #data = urllib2.urlopen("http://hccloudrobotics.pythonanywhere.com/random?board="+input_data)
                data = urllib2.urlopen("http://hccloudrobotics.pythonanywhere.com/engine?board="+input_data)
                play = data.read();
                data.close()
        except:
                pass;
        return play


if __name__ == "__main__":
        print engine(raw_input())