import logging
import time

from edgex.edgex_client import ClientThread
from edgex.event_creator import EventThread

logging.getLogger().setLevel(logging.INFO)
proxy_thread = ClientThread("8081")
proxy_thread.start()
time.sleep(4)
event_thread = EventThread()
event_thread.start()
