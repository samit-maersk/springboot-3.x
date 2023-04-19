#!/bin/zsh
PID=$(pgrep springboot-3.x.x)
RSS=$(ps -o rss ${PID} | tail -n1)
RSS=$(bc <<< "scale=1; ${RSS}/1024")
echo "${PID} ${RSS}M"

#ps -A | grep springboot-3.x.x
#kill -9 PID