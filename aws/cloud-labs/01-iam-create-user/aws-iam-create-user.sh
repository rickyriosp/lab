#!/bin/bash
# Purpose: Automated user creation in AWS
# How To: ./aws-iam-create-user.sh <input-file-name.csv>
# Input file column name: user, group, password
# Author: Ricky Rios
# -------------------------------------------------------

INPUT=$1

# IFS: Internal Field Separator
# Default IFS: space, tab, newline
OLDIFS=$IFS
IFS=',;'

[ ! -f $INPUT ] && { echo "$INPUT file not found"; exit 99; }

command -v dos2unix >/dev/null || { 
	echo "dos2unix tool not found. Please install dos2unix before running the script."; 
	exit 1; 
}

dos2unix "$INPUT" 2>/dev/null

while read -r user group password || [ -n "$user" ]
do
	if [ $user != "user" ]; then
		aws iam create-user --user-name $user
		aws iam create-login-profile --password-reset-required --user-name $user --password $password
		aws iam add-user-to-group --group-name $group --user-name $user
	fi
done < "$INPUT"

IFS=$OLDIFS
