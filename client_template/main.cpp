#include <iostream>
#include <memory>
#include <string>
#include <cstdlib>
#include <unistd.h>

#include "hereos/echo.h"

int main(int argc, char **argv)
{
  EchoClient *echo = new EchoClient();

  while (true) {
    int measurement = rand() % 100;

    std::string reply = echo->Shout(std::to_string(measurement));
    std::cout << "Echo Client received: " << reply << std::endl;

    usleep(1000000);
  }
  return 0;
}
