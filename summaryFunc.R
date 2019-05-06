getMax <- function(datad, columnName)
{
  max(datad[,columnName])
}

getMin <- function(datad, columnName)
{
  min(datad[,columnName])
}

getMean <- function(datad, columnName)
{
  mean(datad[,columnName])
}
