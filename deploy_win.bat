REM This bat file should be executed using Git Bash (sh --login -i)
export PS1='$'
cd %HOMEDRIVE%%HOMEPATH%
rm -rf KV004/
git clone https://github.com/ITsvetkoFF/KV004.git
cd KV004/frontend/
bower install
npm install
grunt distOn
grunt
cd ../backend
npm install
mysql -u root -proot < EnviromapDB.sql
node filldb.js
node api