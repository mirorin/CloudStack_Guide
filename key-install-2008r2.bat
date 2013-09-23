@ECHO OFF
cscript //nologo c:\windows\system32\slmgr.vbs -ipk XXXXX-XXXXX-XXXXX-XXXXX-XXXXX
cscript //nologo c:\windows\system32\slmgr.vbs -cpky
del /f %~f0
