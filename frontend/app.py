import subprocess
from flask import Flask, redirect, url_for, request, render_template
app = Flask(__name__)
 
 
@app.route('/success/<pw>')
def success(pw):
    #p = subprocess.Popen("javac test.java;java test", shell=True)
    #command = "javac test.java;java test " + pw
    #p = subprocess.check_output(command, shell=True)

    #res = p.decode("utf-8")
    #return render_template('base1.html')
    return 'The password you sent is %s' % pw

# @app.route('/success/<name>')
# def success(name):
#     return 'welcome %s' % name

# @app.route('/login', methods=['POST', 'GET'])
# def send():
#     if request.method == 'POST':
#         pw = request.form['pw']
 
@app.route('/', methods=['POST', 'GET'])
def login():
    if request.method == 'POST':
        user = request.form['pw']
        return redirect(url_for('success', pw=user))
    else:
        user = request.args.get('pw')
        return redirect(url_for('success', pw=user))
 
 
if __name__ == '__main__':
    app.run(debug=True)