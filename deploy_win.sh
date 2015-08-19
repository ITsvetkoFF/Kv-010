# This bat file should be executed using Git Bash (sh --login -i)
# Delete KV004 manually before running this script if it exists
cd $HOMEDRIVE$HOMEPATH
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